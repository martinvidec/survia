package at.videc.survia.node.configuration.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.stereotype.Component;

/**
 * This class is used to configure the permission evaluator for the method security.
 */
@Component
public class SurviaMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    /**
     * This method returns the permission evaluator.
     * @return
     */
    @Override
    protected PermissionEvaluator getPermissionEvaluator() {
        return new SurviaPermissionEvaluator();
    }

}
