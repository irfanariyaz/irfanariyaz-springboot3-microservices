package com.code.microservices.order.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIconfig {

    @Bean
    public OpenAPI orderServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Service API")
                        .description("This is a REST API  OpenAPI documentation for order server.")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation().description("you can refer to the Order Service wiki Documentation")
                        .url("https://order-service-dummy-url.com/docs"));
    }
}
