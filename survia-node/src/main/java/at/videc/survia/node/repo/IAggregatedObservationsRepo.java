package at.videc.survia.node.repo;

import at.videc.survia.core.domain.model.IIndicator;
import at.videc.survia.node.domain.model.AggregatedObservation;
import at.videc.survia.node.domain.model.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource(exported = false)
public interface IAggregatedObservationsRepo extends JpaRepository<AggregatedObservation, Long> {
    Iterable<AggregatedObservation> findAllByIndicatorIsIn(Iterable<Indicator> indicators);
}
