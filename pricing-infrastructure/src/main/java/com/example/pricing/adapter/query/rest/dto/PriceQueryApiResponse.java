package com.example.pricing.adapter.query.rest.dto;

import static java.util.Objects.requireNonNull;

import com.example.pricing.port.query.dto.PriceQueryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

/**
 * Represents a price query response.
 *
 * @param brandId The brand ID.
 * @param productId The product ID.
 * @param priceListId The price list ID.
 * @param startDate The start date.
 * @param endDate The end date.
 * @param price The retail price.
 * @param currency The currency.
 */
@Schema(description = "Represents a price query response")
public record PriceQueryApiResponse(
    @Schema(description = "The brand ID") long brandId,
    @Schema(description = "The product ID") long productId,
    @Schema(description = "The price list ID") long priceListId,
    @Schema(description = "The from date") @NotNull Instant startDate,
    @Schema(description = "The to date") @NotNull Instant endDate,
    @Schema(description = "The retail price") @NotNull BigDecimal price,
    @Schema(description = "The currency") @NotNull Currency currency)
    implements PriceQueryResponse {

  /**
   * Creates a new instance.
   *
   * @param brandId The brand ID.
   * @param productId The product ID.
   * @param priceListId The price list ID.
   * @param startDate The start date.
   * @param endDate The end date.
   * @param price The retail price.
   * @param currency The currency.
   */
  public PriceQueryApiResponse {
    requireNonNull(startDate);
    requireNonNull(endDate);
    requireNonNull(price);
    requireNonNull(currency);
    if (!startDate.isBefore(endDate)) {
      throw new IllegalArgumentException("start date must be before end date");
    }
    if (price.signum() < 0) {
      throw new IllegalArgumentException("price can't be negative");
    }
  }
}
