package com.Flock.global.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@OpenAPIDefinition(
        info = @Info(title = "User-Service API 명세서",
                description = "admin : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTcwNDYyODA0MSwiZXhwIjoxNzA5ODEyMDQxfQ.Hsuc1CdEReJLQpS0mQmSQyz3Sgr6ysh5S0BhA2QYxXo \n\n\n\n\n\n" +
                        "user : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmNkIiwicm9sZSI6IlVTRVIiLCJpYXQiOjE3MDQ2MjgxMDcsImV4cCI6MTcwOTgxMjEwN30.WdqS7HCwYFHTSJMM55_uo6qmoj23FEoJg6JuQIl0HEA ",
                version = "v1"
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(Arrays.asList(securityRequirement));
    }
}
