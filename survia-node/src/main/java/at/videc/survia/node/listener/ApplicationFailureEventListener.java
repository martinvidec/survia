package at.videc.survia.node.listener;

import at.videc.survia.node.event.ApplicationFailureEvent;
import at.videc.survia.node.service.api.IApplicationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationFailureEventListener implements ApplicationListener<ApplicationFailureEvent> {

    private final IApplicationStatusService applicationStatusService;

    @Autowired
    public ApplicationFailureEventListener(IApplicationStatusService applicationStatusService) {
        this.applicationStatusService = applicationStatusService;
    }

    @Override
    public void onApplicationEvent(ApplicationFailureEvent applicationFailureEvent) {
        this.applicationStatusService.changeApplicationStatus(applicationFailureEvent);
    }
}
