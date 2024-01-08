package at.videc.survia.node.controller;

import at.videc.survia.node.configuration.properties.SurviaInfoProperties;
import at.videc.survia.node.domain.dto.ApplicationResponse;
import at.videc.survia.node.domain.dto.UserInfoResponse;
import at.videc.survia.node.service.api.IApplicationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController()
//@RepositoryRestController
public class StatusController {

    private final SurviaInfoProperties infoProperties;

    private final IApplicationStatusService applicationStatusService;

    @Autowired
    public StatusController(SurviaInfoProperties infoProperties, IApplicationStatusService applicationStatusService) {
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
