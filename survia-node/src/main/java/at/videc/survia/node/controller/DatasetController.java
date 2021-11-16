package at.videc.survia.node.controller;

import at.videc.survia.node.domain.model.Dataset;
import at.videc.survia.node.repo.IDatasetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * overrides spring-data-rest response handler
 * see https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.overriding-sdr-response-handlers
 */
@RepositoryRestController
public class DatasetController {

    private final IDatasetRepo datasetRepo;

    @Autowired
    public DatasetController(IDatasetRepo datasetRepo) {
        this.datasetRepo = datasetRepo;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/datasets/search")
    @ResponseBody
    public ResponseEntity<CollectionModel<Dataset>> searchDatasets(@RequestParam Pageable page) {
        List<Dataset> datasetList = datasetRepo.findAll(page.getSort());

        CollectionModel<Dataset> resources = CollectionModel.of(datasetList);

        resources.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DatasetController.class)).withSelfRel());

        return ResponseEntity.ok(resources);
    }

}
