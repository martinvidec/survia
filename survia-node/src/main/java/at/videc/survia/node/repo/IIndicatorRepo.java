package at.videc.survia.node.repo;

import at.videc.survia.node.domain.model.Dataset;
import at.videc.survia.node.domain.model.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(exported = false)
public interface IIndicatorRepo extends JpaRepository<Indicator, Long> {
    Iterable<Indicator> findAllByDataset(Dataset dataset);
}
