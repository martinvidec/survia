package at.videc.survia.node.configuration.security;

import at.videc.survia.node.configuration.properties.SecurityProperties;
import at.videc.survia.node.security.SurviaGrantedAuthoritiesConverter;
import at.videc.survia.node.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * OAUTH2 Security Configuration
 */
@Configuration
@EnableWebSecurity
public class OAuth2ResourceSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private IUserService userService;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        if(securityProperties.isDisable()) {
            http.cors(httpSecurityCorsConfigurer -> {
                httpSecurityCorsConfigurer.configurationSource(getCorsConfigurationSource());
            }).authorizeRequests().anyRequest().permitAll();
        } else {
            http.authorizeRequests()
                    .antMatchers("/survia-api", "/status").permitAll()
                    .anyRequest().authenticated()
                    .and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
        }
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        SurviaGrantedAuthoritiesConverter grantedAuthoritiesConverter = new SurviaGrantedAuthoritiesConverter(userService);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        return getCorsConfigurationSource();
    }

    private UrlBasedCorsConfigurationSource getCorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:9090"));
        configuration.setAllowedMethods(Arrays.asList(CorsConfiguration.ALL));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
