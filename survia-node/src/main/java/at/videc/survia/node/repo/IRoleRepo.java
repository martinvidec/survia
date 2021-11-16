package at.videc.survia.node.repo;

import at.videc.survia.node.domain.model.Role;
import at.videc.survia.node.domain.model.constants.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RepositoryRestResource(exported = false)
public interface IRoleRepo extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
    Set<Role> findByNameIn(Set<RoleName> names);
}
