package at.videc.survia.node.security;

import at.videc.survia.node.domain.model.Role;
import at.videc.survia.node.domain.model.User;
import at.videc.survia.node.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class SurviaGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    public static final String USERNAME_CLAIM = "preferred_username";

    private JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

    private IUserService userService;

    public SurviaGrantedAuthoritiesConverter(IUserService userService) {
        this.jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        this.userService = userService;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = jwtGrantedAuthoritiesConverter.convert(jwt);

        String username = (String) jwt.getClaims().get(USERNAME_CLAIM);
        AtomicReference<User> userReference = new AtomicReference<>();

        AuthUtil.runAsSystem(() -> {
            userReference.set(userService.getUser(username));
        });

        if(userReference.get() == null) {
            return grantedAuthorities;
        }

        Collection<? extends GrantedAuthority> privateGrantedAuthorities = userReference.get().getRoles().stream().map(this::roleToAuthority).collect(Collectors.toList());

        grantedAuthorities.addAll(privateGrantedAuthorities);

        return grantedAuthorities;
    }

    public void setAuthorityPrefix(String authorityPrefix) {
        this.jwtGrantedAuthoritiesConverter.setAuthorityPrefix(authorityPrefix);
    }

    public void setAuthoritiesClaimName(String authoritiesClaimName) {
        this.jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(authoritiesClaimName);
    }

    private SimpleGrantedAuthority roleToAuthority(Role role) {
        return new SimpleGrantedAuthority(role.getName().name());
    }
}
