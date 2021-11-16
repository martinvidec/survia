package at.videc.survia.node.listener;

import at.videc.survia.node.domain.model.constants.ApplicationStatus;
import at.videc.survia.node.service.api.IApplicationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    private final IApplicationStatusService applicationStatusService;

    @Autowired
    public ContextRefreshedListener(IApplicationStatusService applicationStatusService) {
        this.applicationStatusService = applicationStatusService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        applicationStatusService.setApplictionStatus(ApplicationStatus.RUNNING);
    }
}
