package com.chatop.backend.web.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "ChâTop API",
        version = "1.0",
        description = "API REST de mise en relation entre locataires et propriétaires. "
            + "Hors inscription et connexion, chaque route exige un JWT : "
            + "récupérez-le via /api/auth/login puis renseignez-le dans « Authorize »."
    ),
    security = @SecurityRequirement(name = OpenApiConfig.BEARER_AUTH)
)
@SecurityScheme(
    name = OpenApiConfig.BEARER_AUTH,
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class OpenApiConfig {

    static final String BEARER_AUTH = "bearerAuth";
}
