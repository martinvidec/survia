package at.videc.survia.node.configuration;

import at.videc.survia.node.configuration.properties.InfoProperties;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

//    @Autowired
//    private InfoProperties infoProperties;

//    @Bean
//    public OpenAPI surviaOpenApi(@Autowired InfoProperties infoProperties) {
//        return new OpenAPI()
//                .info(new Info().title(infoProperties.getApiTitle())
//                        .description(infoProperties.getApiDescription())
//                        .version(infoProperties.getApiVersion())
//                        .license(new License().name(infoProperties.getApiLicense()).url(infoProperties.getApiLicenseUrl())))
//                .externalDocs(new ExternalDocumentation()
//                .description(infoProperties.getApiExternalDocDescription())
//                .url(infoProperties.getApiExternalDocUrl()));
//    }

    @Bean
    public GroupedOpenApi surviaGroupedOpenApi(@Autowired InfoProperties infoProperties) {
        return GroupedOpenApi.builder().group("survia-api").packagesToScan("at.videc.survia").addOpenApiCustomiser(openApi -> {
            openApi.info(new Info().title(infoProperties.getApiTitle())
                        .description(infoProperties.getApiDescription())
                        .version(infoProperties.getApiVersion())
                        .license(new License().name(infoProperties.getApiLicense()).url(infoProperties.getApiLicenseUrl())))
                .externalDocs(new ExternalDocumentation()
                .description(infoProperties.getApiExternalDocDescription())
                .url(infoProperties.getApiExternalDocUrl()));
        }).build();
    }

}
