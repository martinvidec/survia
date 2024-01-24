package at.videc.survia.node.configuration.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;

public class KeycloakRealmRolesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        if (realmAccess == null || realmAccess.isEmpty()) {
            return new ArrayList<>();
        }

        Collection<GrantedAuthority> roles = new ArrayList<>();
        ((List<String>) realmAccess.get("roles")).forEach(roleName -> {
            if(roleName.startsWith("ROLE_"))
                roles.add(new SimpleGrantedAuthority(roleName));
            else {
                roles.add(new SimpleGrantedAuthority("ROLE_" + roleName));
            }
        });

        return roles;
    }
}
