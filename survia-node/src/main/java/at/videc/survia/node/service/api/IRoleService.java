package at.videc.survia.node.service.api;

import at.videc.survia.node.domain.model.Role;
import at.videc.survia.node.domain.model.constants.RoleName;

import java.util.Set;

public interface IRoleService {
    Role createRole(RoleName name);

    Role getRole(RoleName roleName);

    Set<Role> getRoles(Set<RoleName> roleNames);
}
