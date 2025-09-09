package com.example.pricing.config;

import com.example.pricing.port.persistence.PricePersistencePort;
import com.example.pricing.service.PricingService;
import com.example.pricing.service.PricingServiceImpl;
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
}
