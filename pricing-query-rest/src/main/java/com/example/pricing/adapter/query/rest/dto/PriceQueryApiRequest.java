package com.example.pricing.adapter.query.rest.dto;

import static java.util.Objects.requireNonNull;

import com.example.pricing.port.query.dto.PriceQueryRequest;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

/**
 * Represents a price query request.
 *
 * @param brandId The brand ID.
 * @param productId The product ID.
 * @param date The point in time.
 */
public record PriceQueryApiRequest(long brandId, long productId, @NotNull Instant date)
    implements PriceQueryRequest {

  /**
   * Creates a new instance.
   *
   * @param brandId The brand ID.
   * @param productId The product ID.
   * @param date The point in time.
   */
  public PriceQueryApiRequest {
    requireNonNull(date);
  }
}
