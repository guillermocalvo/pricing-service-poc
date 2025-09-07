package com.example.pricing.domain;

import static java.util.Objects.requireNonNull;

import jakarta.validation.constraints.NotNull;

/**
 * Aggregate object that represents a product price at a specific time range.
 *
 * @param id The price ID.
 * @param brandId The brand ID.
 * @param productId The product ID.
 * @param priceListId The price list ID.
 * @param priority The priority (in case of overlap).
 * @param period The validity period.
 * @param amount The retail price.
 */
public record Price(
    long id,
    long brandId,
    long productId,
    long priceListId,
    int priority,
    @NotNull ValidityPeriod period,
    @NotNull MoneyAmount amount) {

  /**
   * Creates a new instance.
   *
   * @param id The price ID.
   * @param brandId The brand ID.
   * @param productId The product ID.
   * @param priceListId The price list ID.
   * @param priority The priority (in case of overlap).
   * @param period The validity period.
   * @param amount The retail price.
   */
  public Price {
    requireNonNull(period);
    requireNonNull(amount);
  }
}
