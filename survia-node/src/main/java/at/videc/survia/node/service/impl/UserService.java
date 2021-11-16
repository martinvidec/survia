package at.videc.survia.node.service.impl;

import at.videc.survia.node.domain.model.Role;
import at.videc.survia.node.domain.model.User;
import at.videc.survia.node.domain.model.constants.RoleName;
import at.videc.survia.node.repo.IUserRepo;
import at.videc.survia.node.service.api.IRoleService;
import at.videc.survia.node.service.api.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements IUserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final IUserRepo userRepo;

    private final IRoleService roleService;

    @Autowired
    public UserService(IUserRepo userRepo, IRoleService roleService) {
        this.userRepo = userRepo;
        this.roleService = roleService;
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User createAdminUser(String username) {
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleService.getRole(RoleName.ROLE_ADMIN));
        adminRoles.add(roleService.getRole(RoleName.ROLE_USER));

        User admin = create(username, adminRoles);

        LOG.info("User '{}' Id: {} created as admin", admin.getUsername(), admin.getId());
        return admin;
    }

    @Override
    public User createDefaultUser(String username) {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.getRole(RoleName.ROLE_USER));

        User user = create(username, userRoles);

        LOG.info("User '{}' created", user.getUsername());
        return user;
    }

    private User create(String username, Set<Role> userRoles) {
        User user = new User();
        user.setUsername(username);
        user.setRoles(userRoles);
        userRepo.save(user);
        return user;
    }
}
