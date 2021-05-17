package de.strasser.peter.hexagonal.web.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {


  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components())
        .info(
            new Info()
                .title("Customer Service API")
                .version("v0.0.1")
                .description("""
This service serves as demo project to showcase hexagonal architecture, with some operations on customers with addresses.
""" ));
  }
}
