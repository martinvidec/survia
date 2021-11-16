package at.videc.survia.node.configuration;

import at.videc.survia.node.configuration.properties.AdminProperties;
import at.videc.survia.node.configuration.properties.InfoProperties;
import at.videc.survia.node.configuration.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SecurityProperties.class, AdminProperties.class, InfoProperties.class})
public class AppConfig {

    @Autowired
    private SecurityProperties properties;
}
