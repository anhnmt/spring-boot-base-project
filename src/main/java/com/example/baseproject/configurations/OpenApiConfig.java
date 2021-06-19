package com.example.baseproject.configuration;

import com.example.baseproject.common.utils.Constants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(Constants.AUTHORIZATION))
                .components(
                        new Components()
                                .addSecuritySchemes(Constants.AUTHORIZATION,
                                        new SecurityScheme()
                                                .name(Constants.AUTHORIZATION)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                // Thiết lập các server dùng để test api
                .servers(Collections.singletonList(
                        new Server().url("http://localhost:8080")
                ))
                // info
                .info(new Info().title("Spring Application API")
                        .description("Sample OpenAPI 3.0")
                        .contact(new Contact()
                                .email("xdorro@gmail.com")
                                .name("xdorro")
                                .url("https://github.com/xdorro"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                        .version("1.0.0"));
    }
}
