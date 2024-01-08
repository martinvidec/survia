package at.videc.survia.node.configuration;

import at.videc.survia.node.domain.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@Component
public class RestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Dataset.class, Country.class, Indicator.class, MeasurementUnit.class, Observation.class, Role.class, User.class);

        // only export explicitly annotated repositories (@RepositoryRestResource)
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);

//        config.setDefaultMediaType(MediaType.APPLICATION_JSON);

        // TODO only if SecurityProperties are disabled
        cors.addMapping("/**").allowedOrigins(CorsConfiguration.ALL);
//        cors.addMapping("/api").allowedOrigins("http://localhost");
//        cors.addMapping("/status").allowedOrigins("http://localhost");
    }
}
