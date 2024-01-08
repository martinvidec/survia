package at.videc.survia.node.configuration;

import at.videc.survia.node.configuration.properties.SurviaInfoProperties;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    public static final String SURVIA_API = "survia-api";
    public static final String AT_VIDEC_SURVIA_NODE = "at.videc.survia.node";

    @Autowired
    private SurviaInfoProperties infoProperties;

    @Bean
    public GroupedOpenApi surviaGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group(SURVIA_API)
                .packagesToScan(AT_VIDEC_SURVIA_NODE)
                .addOpenApiCustomiser(new OpenApiCustomiser() {
                    @Override
                    public void customise(OpenAPI openApi) {
                            openApi.info(new Info().title(infoProperties.getApiTitle())
                                            .description(infoProperties.getApiDescription())
                                            .version(infoProperties.getApiVersion())
                                            .license(new License().name(infoProperties.getApiLicense()).url(infoProperties.getApiLicenseUrl())))
                                    .externalDocs(new ExternalDocumentation()
                                            .description(infoProperties.getApiExternalDocDescription())
                                            .url(infoProperties.getApiExternalDocUrl()));
                    }
                }).build();
    }



}
