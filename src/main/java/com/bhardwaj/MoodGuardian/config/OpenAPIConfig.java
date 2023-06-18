package com.bhardwaj.MoodGuardian.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Ankit Bhardwaj",
                        email = "iamankitbhardwaj@gmail.com"
                ),
                description = "OpenAPI specification for MoodGuardian Application.",
                title = "OpenAPI Specification - MoodGuardian",
                version = "1.0"
        ),
        externalDocs = @ExternalDocumentation(
                description = "WARNING: Integer/Long values in response are not always correct ones, this is due to limitations of Javascript parsing BigInts.\n" +
                        "To correctly test this API use application like Postman.",
                url = "https://github.com/swagger-api/swagger-ui/issues/2030"
        ),
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Bearer Authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfig {
}

