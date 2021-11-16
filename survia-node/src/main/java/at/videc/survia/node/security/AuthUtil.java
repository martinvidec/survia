package at.videc.survia.node.security;

import at.videc.survia.node.domain.model.constants.RoleName;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

public class AuthUtil {

    public static final String SYSTEM = "system";

    public static void runAsSystem(Runnable runnable) {
        final AnonymousAuthenticationToken token = new AnonymousAuthenticationToken(SYSTEM, SYSTEM, Arrays.asList(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.name())));

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(token);
        runnable.run();
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
