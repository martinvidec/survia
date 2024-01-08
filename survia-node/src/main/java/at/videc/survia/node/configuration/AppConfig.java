package at.videc.survia.node.configuration;

import at.videc.survia.node.configuration.properties.SurviaAdminProperties;
import at.videc.survia.node.configuration.properties.SurviaInfoProperties;
import at.videc.survia.node.configuration.properties.SurviaSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SurviaSecurityProperties.class, SurviaAdminProperties.class, SurviaInfoProperties.class})
public class AppConfig {

    @Autowired
    private SurviaSecurityProperties properties;
}
