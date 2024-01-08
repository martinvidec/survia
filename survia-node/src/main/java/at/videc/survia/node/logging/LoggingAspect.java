package at.videc.survia.node.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(org.springframework.security.access.prepost.PreAuthorize)")
    public void preAuthorizeMethods() {}

    @Around("preAuthorizeMethods()")
    public Object logJwt(ProceedingJoinPoint joinPoint) throws Throwable {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String jwt = authentication.getToken().getTokenValue();

        logger.debug("JWT: {}", jwt);

        return joinPoint.proceed();
    }
}
