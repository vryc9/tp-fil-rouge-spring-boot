package edu.esiea.tp_fil_rouge.utils.security.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("TP Fil Rouge API").version("1.0"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

    @Bean
    public OperationCustomizer customize() {
        return (operation, handlerMethod) -> {
            // On récupère l'annotation sur la méthode ou la classe
            PreAuthorize preAuthorize = handlerMethod.getMethodAnnotation(PreAuthorize.class);
            if (preAuthorize == null) {
                preAuthorize = handlerMethod.getBeanType().getAnnotation(PreAuthorize.class);
            }

            if (preAuthorize != null) {
                operation.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));

                String roleExpression = preAuthorize.value();
                String securityNote = "\n\n---\n**🔐 Autorisation requise :** `" + roleExpression + "`";

                String existingDesc = operation.getDescription();
                if (existingDesc == null) {
                    operation.setDescription(securityNote);
                } else {
                    operation.setDescription(existingDesc + securityNote);
                }
            }
            return operation;
        };
    }
}
