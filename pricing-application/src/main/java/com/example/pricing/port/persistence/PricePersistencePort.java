package com.example.pricing.port.persistence;

import com.example.pricing.domain.Price;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;

/** Handles price persistence. */
public interface PricePersistencePort {

  /**
   * Finds the price of a given product at a specific point in time.
   *
   * @param brandId The brand ID.
   * @param productId The product ID.
   * @param date The point in time.
   * @return An optional appropriate price for the product.
   */
  @NotNull
  Optional<Price> findPrice(long brandId, long productId, @NotNull Instant date);
}
