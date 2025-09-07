package com.example.pricing.adapter.query.rest.dto;

import static java.util.Objects.requireNonNull;

import com.example.pricing.port.query.dto.PriceQueryRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

/**
 * Represents a price query request.
 *
 * @param brandId The brand ID.
 * @param productId The product ID.
 * @param date The point in time.
 */
@Schema(description = "Represents a price query request")
public record PriceQueryApiRequest(
    @Schema(description = "The brand ID", example = "1") long brandId,
    @Schema(description = "The product ID", example = "35455") long productId,
    @Schema(description = "The point in time", example = "2020-07-07T12:30:00Z") @NotNull
        Instant date)
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
