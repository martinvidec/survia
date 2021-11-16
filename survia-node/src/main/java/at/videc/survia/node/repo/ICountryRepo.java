package at.videc.survia.node.repo;

import at.videc.survia.node.domain.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(exported = false)
public interface ICountryRepo extends CrudRepository<Country, Long> { }
