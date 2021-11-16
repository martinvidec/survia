package at.videc.survia.node.repo;

import at.videc.survia.node.domain.model.Dataset;
import at.videc.survia.node.domain.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(exported = false)
public interface IObservationRepo extends JpaRepository<Observation, Long> {

    Iterable<Observation> findAllByDataset(Dataset dataset);

}
