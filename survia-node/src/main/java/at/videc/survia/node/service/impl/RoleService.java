package at.videc.survia.node.service.impl;

import at.videc.survia.node.domain.model.Role;
import at.videc.survia.node.domain.model.constants.RoleName;
import at.videc.survia.node.repo.IRoleRepo;
import at.videc.survia.node.service.api.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService implements IRoleService {

    private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

    private final IRoleRepo roleRepo;

    @Autowired
    public RoleService(IRoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role createRole(RoleName name) {
        Role userRole = new Role();
        userRole.setName(name);
        roleRepo.save(userRole);
        LOG.info("Role '{}' created", name);
        return userRole;
    }

    @Override
    public Role getRole(RoleName roleName) {
        return roleRepo.findByName(roleName);
    }

    @Override
    public Set<Role> getRoles(Set<RoleName> roleNames) {
        return roleRepo.findByNameIn(roleNames);
    }

}
