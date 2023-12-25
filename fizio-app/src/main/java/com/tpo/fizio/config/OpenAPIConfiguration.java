package com.tpo.fizio.config;

import com.tpo.fizio.security.SecurityProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI integrationServerOpenApi(List<SecurityScheme> securityScheme) {
        OpenAPI openAPI = new OpenAPI()
                .info(
                        new Info()
                                .title("Physiotherapist Server API DEMO")
                                .description("REST API for Physiotherapist")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("TPO")
                                                .email("tpo@tpo.com")
                                                .url("https://www.tpo.care")));

        Components components = new Components();
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityScheme.forEach(it -> {
            securityRequirement.addList(it.getName());
            components.addSecuritySchemes(it.getName(), it);
        });

        if (!securityRequirement.isEmpty()) {
            openAPI.addSecurityItem(securityRequirement);
        }

        openAPI.setComponents(components);
        return openAPI;
    }


    @Bean
    public List<SecurityScheme> oauth2SecurityScheme(SecurityProperties securityProperties) {
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setName("oauth2");
        securityScheme.setType(SecurityScheme.Type.OAUTH2);

        OAuthFlow oAuthFlow = new OAuthFlow();
        oAuthFlow.setTokenUrl(securityProperties.getAccessTokenUrl());
        oAuthFlow.setAuthorizationUrl(securityProperties.getAuthorizationUrl());
        securityScheme.setFlows(new OAuthFlows().password(oAuthFlow));

        return Collections.singletonList(securityScheme);
    }

}
