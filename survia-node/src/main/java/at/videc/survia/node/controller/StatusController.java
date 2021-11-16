package at.videc.survia.node.controller;

import at.videc.survia.node.configuration.properties.InfoProperties;
import at.videc.survia.node.domain.dto.ApplicationResponse;
import at.videc.survia.node.domain.model.constants.ApplicationStatus;
import at.videc.survia.node.event.ApplicationFailureEvent;
import at.videc.survia.node.service.api.IApplicationStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

//@RestController()
@RepositoryRestController
public class StatusController {

    private final InfoProperties infoProperties;

    private final IApplicationStatusService applicationStatusService;

    @Autowired
    public StatusController(InfoProperties infoProperties, IApplicationStatusService applicationStatusService) {
        this.infoProperties = infoProperties;
        this.applicationStatusService = applicationStatusService;
    }

    //    @Operation(
//            summary = "Request Status",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Successful Operation",
//                            content = @Content(
//                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
//                                    schema = @Schema(implementation = ApplicationResponse.class)))
//            })
    @RequestMapping(method = RequestMethod.GET, value = "/status")
    @ResponseBody
    public ApplicationResponse status() {
        ApplicationResponse applicationResponse = new ApplicationResponse();
        applicationResponse.setApplicationStatus(applicationStatusService.getApplicationStatus());
        applicationResponse.setApiVersion(infoProperties.getApiVersion());
        applicationResponse.setAuthentication(SecurityContextHolder.getContext().getAuthentication().getName());
        return applicationResponse;
    }
}
