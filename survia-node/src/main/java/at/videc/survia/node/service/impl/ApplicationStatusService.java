package at.videc.survia.node.service.impl;

import at.videc.survia.node.domain.model.constants.ApplicationStatus;
import at.videc.survia.node.event.ApplicationFailureEvent;
import at.videc.survia.node.service.api.IApplicationStatusService;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class ApplicationStatusService implements IApplicationStatusService {

    private ApplicationStatus applicationStatus = ApplicationStatus.STARTING;

    @Override
    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    @Override
    public void setApplictionStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    @Override
    public void changeApplicationStatus(ApplicationFailureEvent applicationFailureEvent) {
        Exception e = applicationFailureEvent.getException();

        // TODO set status based on Exception
        if(DataAccessResourceFailureException.class.equals(e.getClass())) {
            this.applicationStatus = ApplicationStatus.DATABASE_NOT_CONNECTED;
        } else {
            this.applicationStatus = ApplicationStatus.RUNNING_WITH_FAILURES;
        }
    }
}
