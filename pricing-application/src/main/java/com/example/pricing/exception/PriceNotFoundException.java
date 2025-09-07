package com.example.pricing.exception;

import java.time.Instant;

/** Thrown when a price cannot be found for a product at a specific point in time. */
public class PriceNotFoundException extends PricingException {

  /** The brand ID. */
  public final long brandId;

  /** The product ID. */
  public final long productId;

  /** The point in time. */
  public final Instant date;

  /**
   * Creates a new instance.
   *
   * @param brandId The brand ID.
   * @param productId The product ID.
   * @param date The point in time.
   */
  public PriceNotFoundException(long brandId, long productId, Instant date) {
    super("Price not found");
    this.brandId = brandId;
    this.productId = productId;
    this.date = date;
  }
}
