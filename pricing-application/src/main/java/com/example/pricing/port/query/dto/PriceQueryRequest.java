package com.example.pricing.port.query.dto;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

/** Represents a price query request. */
public interface PriceQueryRequest {

  /**
   * Returs the brand ID.
   *
   * @return The brand ID.
   */
  long brandId();

  /**
   * Returns the product ID.
   *
   * @return The product ID.
   */
  long productId();

  /**
   * Returns the point in time.
   *
   * @return The point in time.
   */
  @NotNull
  Instant date();
}
