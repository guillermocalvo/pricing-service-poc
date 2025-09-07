package com.example.pricing.port.query.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

/** Represents a price query response. */
public interface PriceQueryResponse {

  /**
   * Returns the brand ID.
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
   * Returns the price list ID.
   *
   * @return The price list ID.
   */
  long priceListId();

  /**
   * Returns the start date.
   *
   * @return The start date.
   */
  @NotNull
  Instant startDate();

  /**
   * Returns the end date.
   *
   * @return The end date.
   */
  @NotNull
  Instant endDate();

  /**
   * Returns the retail price.
   *
   * @return The retail price.
   */
  @NotNull
  BigDecimal price();

  /**
   * Returns the currency.
   *
   * @return The currency.
   */
  @NotNull
  Currency currency();
}
