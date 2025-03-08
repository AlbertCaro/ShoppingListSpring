package dev.albertocaro.shopping_list.di

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Configuration

@Configuration
@SecurityScheme(
    name = OpenApiConfig.SECURITY_SCHEME_NAME,
    scheme = "bearer",
    type =  SecuritySchemeType.HTTP,
    bearerFormat = "JWT"
)
class OpenApiConfig {

    companion object {
        const val SECURITY_SCHEME_NAME = "bearerAuth"
    }
}