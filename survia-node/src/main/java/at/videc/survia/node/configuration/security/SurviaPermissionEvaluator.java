package at.videc.survia.node.configuration.security;

import org.keycloak.AuthorizationContext;
import org.keycloak.authorization.client.ClientAuthorizationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.Serializable;

/**
 * This class is used to evaluate the permissions for the method security.
 */
public class SurviaPermissionEvaluator implements PermissionEvaluator {

    private static final Logger LOG = LoggerFactory.getLogger(SurviaPermissionEvaluator.class);

    /**
     * This method checks if the user has the permission.
     * @param authentication
     * @param targetDomainObject
     * @param permission
     * @return true if the user has the permission, false otherwise
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        LOG.debug(authentication.toString());

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOG.debug("RequestAttributes is null");
            return false;
        }

        ClientAuthorizationContext clientAuthorizationContext = (ClientAuthorizationContext) requestAttributes.getAttribute(AuthorizationContext.class.getName(), RequestAttributes.SCOPE_REQUEST);
        if (clientAuthorizationContext == null) {
            LOG.debug("ClientAuthorizationContext is null");
            return false;
        }

        return clientAuthorizationContext.hasScopePermission(permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        LOG.debug(authentication.toString());
        return false;
    }
}
