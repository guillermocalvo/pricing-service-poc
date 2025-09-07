package com.example.pricing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** Pricing service application. */
@OpenAPIDefinition(
    info =
        @Info(
            title = "Pricing Service",
            description = "Pricing service proof of concept",
            version = "1.0",
            contact =
                @Contact(
                    url = "https://guillermo.dev",
                    name = "Guillermo Calvo",
                    email = "hello@guillermo.dev"),
            license =
                @License(
                    name = "Apache 2.0",
                    url = "http://www.apache.org/licenses/LICENSE-2.0.html")),
    tags = @Tag(name = "price", description = "Product prices"),
    servers = @Server(url = "http://localhost:8080", description = "DEV"))
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.pricing.adapter.persistence.jpa")
@EntityScan(basePackages = "com.example.pricing.adapter.persistence.jpa.entity")
public class PricingServiceApplication {

  private PricingServiceApplication() {}

  /**
   * Runs the Spring application.
   *
   * @param args The arguments.
   */
  @Generated // Exclude from coverage
  public static void main(String[] args) {
    SpringApplication.run(PricingServiceApplication.class, args);
  }
}
