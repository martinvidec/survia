package at.videc.survia.node.configuration.security;

import at.videc.survia.node.configuration.properties.SurviaSecurityProperties;
import at.videc.survia.node.service.api.IUserService;
import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SurviaSecurityConfig {

    private final SurviaSecurityProperties securityProperties;

    private final CorsConfigurationSource corsConfigurationSource;
    private final IUserService userService;

    public SurviaSecurityConfig(
            @Autowired SurviaSecurityProperties securityProperties,
            @Autowired CorsConfigurationSource corsConfigurationSource,
            @Autowired IUserService userService
    ) {
        this.securityProperties = securityProperties;
        this.corsConfigurationSource = corsConfigurationSource;
        this.userService = userService;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (securityProperties.isDisable()) {
            http.csrf(AbstractHttpConfigurer::disable)
                    .cors(httpSecurityCorsConfigurer -> {
                        httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource);
                    }).authorizeHttpRequests(authorize -> {
                        authorize.anyRequest().authenticated();
                    });
        } else {
            http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                    .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(jwt -> {
                        jwt.decoder(jwtDecoder());
                        jwt.jwtAuthenticationConverter(jwtAuthenticationConverter());
                    }))
                    .addFilterAfter(createPolicyEnforcerFilter(), BearerTokenAuthenticationFilter.class);
        }
        return http.build();
    }

    private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
        return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
            @Override
            public PolicyEnforcerConfig resolve(HttpRequest httpRequest) {
                PolicyEnforcerConfig config = new PolicyEnforcerConfig();
                config.setRealm(securityProperties.getRealm());
                config.setAuthServerUrl(securityProperties.getAuthServerUrl());
                config.setResource(securityProperties.getResource());
                config.getCredentials().put("secret", securityProperties.getSecret());
                return config;
            }
        });
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.securityProperties.getJwkSetUri()).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRolesConverter());
        return converter;
    }

//    /**
//     * This bean is required to enable debug mode for Spring Security.
//     *
//     * @return
//     */
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.debug(true);
//    }
}
