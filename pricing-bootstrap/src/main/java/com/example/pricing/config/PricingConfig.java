package com.example.pricing.config;

import com.example.pricing.adapter.query.rest.dto.PriceQueryApiRequest;
import com.example.pricing.port.persistence.PricePersistencePort;
import com.example.pricing.port.query.dto.PriceQueryRequest;
import com.example.pricing.service.PricingService;
import com.example.pricing.service.PricingServiceImpl;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configures the pricing application. */
@Configuration
public class PricingConfig {

  /** Creates a new instance. */
  public PricingConfig() {
    // Does nothing
  }

  /**
   * Creates a pricing pricingService bean.
   *
   * @param pricePersistence The price persistence.
   * @return A new pricing pricingService instance.
   */
  @Bean
  PricingService pricingService(PricePersistencePort pricePersistence) {
    return new PricingServiceImpl(pricePersistence);
  }

  /** Jackson mixin to deserialize price query requests. */
  @JsonDeserialize(as = PriceQueryApiRequest.class)
  public interface PriceQueryRequestMixin {}

  /**
   * Instantiates a Jackson module that sets up mixins.
   *
   * @return A Jackson module that sets up mixins.
   */
  @Bean
  public Module priceModule() {
    return new SimpleModule()
        .setMixInAnnotation(PriceQueryRequest.class, PriceQueryRequestMixin.class);
  }
}
