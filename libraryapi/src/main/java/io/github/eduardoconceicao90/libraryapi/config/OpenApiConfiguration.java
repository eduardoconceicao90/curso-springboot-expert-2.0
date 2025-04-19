package io.github.eduardoconceicao90.libraryapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Library API",
                version = "1.0",
                description = "API for managing a library system",
                contact = @Contact(
                        name = "Eduardo Conceição",
                        email = "eduardosaconceicao@libraryapi.com",
                        url = "libraryapi.com"
                )
        )
)
public class OpenApiConfiguration {
}
