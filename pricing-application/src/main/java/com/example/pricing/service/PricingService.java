package com.example.pricing.service;

import com.example.pricing.domain.Price;
import java.time.Instant;
import java.util.Optional;

/** Handles product prices. */
public interface PricingService {

  /**
   * Finds the price of a given product at a specific point in time.
   *
   * @param brandId The brand ID.
   * @param productId The product ID.
   * @param date The point in time.
   * @return An optional appropriate price for the product.
   */
  Optional<Price> findCurrentPrice(long brandId, long productId, Instant date);
}
