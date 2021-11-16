package at.videc.survia.node.service.api;

import at.videc.survia.node.domain.model.constants.ApplicationStatus;
import at.videc.survia.node.event.ApplicationFailureEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public interface IApplicationStatusService {

    ApplicationStatus getApplicationStatus();

    void setApplictionStatus(ApplicationStatus applicationStatus);

    void changeApplicationStatus(ApplicationFailureEvent applicationFailureEvent);

}
