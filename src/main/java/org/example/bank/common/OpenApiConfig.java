package org.example.bank.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Jaya Bank API",
                version = "1.0",
                description = "API para avaliação de candidatos - operações bancárias simples.",
                contact = @Contact(name = "Equipe Jaya", email = "tech@jaya.com"),
                license = @License(name = "Proprietary")
        )
)
public class OpenApiConfig {
    // Configurações adicionais podem ser adicionadas aqui (ex: grupos, security).
}

