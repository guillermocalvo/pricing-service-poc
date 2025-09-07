package com.example.pricing.service;

import static java.util.Objects.requireNonNull;

import com.example.pricing.domain.Price;
import com.example.pricing.port.persistence.PricePersistencePort;
import java.time.Instant;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles product prices.
 *
 * @param pricePersistence The price persistence.
 */
@Slf4j
public record PricingServiceImpl(PricePersistencePort pricePersistence) implements PricingService {

  /**
   * Creates a new instance.
   *
   * @param pricePersistence The price persistence.
   */
  public PricingServiceImpl {
    requireNonNull(pricePersistence);
  }

  /** {@inheritDoc} */
  @Override
  public Optional<Price> findCurrentPrice(long brandId, long productId, Instant date) {
    log.info("Finding current price (brand: {}, product: {}, date: {})", brandId, productId, date);
    final Optional<Price> currentPrice = pricePersistence.findPrice(brandId, productId, date);
    currentPrice.ifPresentOrElse(
        x -> log.info("Current price found: {}", x),
        () ->
            log.warn(
                "Could not find current price (brand: {}, product: {}, date: {})",
                brandId,
                productId,
                date));
    return currentPrice;
  }
}
