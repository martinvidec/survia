package at.videc.survia.node.setup;

import at.videc.survia.node.configuration.properties.SurviaAdminProperties;
import at.videc.survia.node.domain.model.User;
import at.videc.survia.node.domain.model.constants.RoleName;
import at.videc.survia.node.event.ApplicationFailureEvent;
import at.videc.survia.node.security.AuthUtil;
import at.videc.survia.node.service.api.IRoleService;
import at.videc.survia.node.service.api.IUserService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class SetupComponent {

    private static final Logger LOG = LoggerFactory.getLogger(SetupComponent.class);

    private final IRoleService roleService;

    private final IUserService userService;

    private final SurviaAdminProperties adminProperties;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public SetupComponent(IRoleService roleService, IUserService userService, SurviaAdminProperties adminProperties, ApplicationEventPublisher eventPublisher) {
        this.roleService = roleService;
        this.userService = userService;
        this.adminProperties = adminProperties;
        this.eventPublisher = eventPublisher;
    }

    @PostConstruct
    public void initSetup() {
        try {
            initSetupAsAdmin();
        } catch (Exception e) {
            LOG.error("initSetup failed.", e);
            eventPublisher.publishEvent(new ApplicationFailureEvent(this, e));
        }

    }

    private void initSetupAsAdmin() {
        AuthUtil.runAsSystem(() -> {
            initRoles();
            initAdmin();
        });
    }

    private void initRoles() {
        if (roleService.getRole(RoleName.ROLE_ADMIN) == null) {
            roleService.createRole(RoleName.ROLE_ADMIN);
        }
        if (roleService.getRole(RoleName.ROLE_EDITOR) == null) {
            roleService.createRole(RoleName.ROLE_EDITOR);
        }
        if (roleService.getRole(RoleName.ROLE_USER) == null) {
            roleService.createRole(RoleName.ROLE_USER);
        }
    }

    private void initAdmin () {
        User user = userService.getUser(adminProperties.getUsername());

        if(user == null) {
            userService.createAdminUser(adminProperties.getUsername());
        }
    }
}
