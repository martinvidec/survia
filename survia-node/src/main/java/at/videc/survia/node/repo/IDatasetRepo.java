package at.videc.survia.node.repo;

import at.videc.survia.node.domain.model.Dataset;
import at.videc.survia.node.domain.model.QDataset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('USER')")
@RepositoryRestResource()
public interface IDatasetRepo extends JpaRepository<Dataset, Long>, QuerydslPredicateExecutor<Dataset>/*, QuerydslBinderCustomizer<QDataset>*/ {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <T extends Dataset> T save(final T entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <T extends Dataset> List<T> saveAll(Iterable<T> var1);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteById(Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Dataset entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAll(Iterable<? extends Dataset> entities);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAll();

//    @Override
//    default void customize(QuerydslBindings bindings, QDataset dataset) {
//        bindings.bind(dataset.name).fi
//    }

}
