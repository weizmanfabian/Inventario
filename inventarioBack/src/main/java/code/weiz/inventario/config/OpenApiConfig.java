package code.weiz.inventario.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Inventario API", version = "1.0", description = "Documentación de la API de Inventario")
)
public class OpenApiConfig {
}
