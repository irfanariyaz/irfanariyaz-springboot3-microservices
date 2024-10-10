package com.code.microservices.inventory.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIconfig {

    @Bean
    public OpenAPI inventoryServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Service API")
                        .description("This is a REST API  OpenAPI documentation for Inventory server.")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation().description("you can refer to the Inventory Service wiki Documentation")
                        .url("https://inventory-service-dummy-url.com/docs"));
    }
}
