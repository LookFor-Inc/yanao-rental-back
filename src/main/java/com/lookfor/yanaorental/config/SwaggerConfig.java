package com.lookfor.yanaorental.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "YANAO Rental REST API",
                description = "REST API service \"YANAO Rental\"",
                version = "1.0.0"))
public class SwaggerConfig {
}
