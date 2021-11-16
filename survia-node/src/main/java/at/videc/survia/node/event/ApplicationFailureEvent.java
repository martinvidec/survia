package at.videc.survia.node.event;

import at.videc.survia.node.domain.model.constants.ApplicationStatus;
import org.springframework.context.ApplicationEvent;

public class ApplicationFailureEvent extends ApplicationEvent {

    private Exception exception;

    public ApplicationFailureEvent(Object source, Exception exception) {
        super(source);
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
