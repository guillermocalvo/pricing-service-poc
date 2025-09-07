package com.example.pricing.domain;

import static java.util.Objects.requireNonNull;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

/**
 * Value object that represents the validity period of a price.
 *
 * @param from The point in time before which the price is not yet applicable.
 * @param to The point in time after which the price is no longer applicable.
 */
public record ValidityPeriod(@NotNull Instant from, @NotNull Instant to) {

  /**
   * Creates a new instance.
   *
   * @param from The point in time before which the price is not yet applicable.
   * @param to The point in time after which the price is no longer applicable.
   */
  public ValidityPeriod {
    requireNonNull(from);
    requireNonNull(to);
    if (!from.isBefore(to)) {
      throw new IllegalArgumentException("from (start date) must be before to (end date)");
    }
  }
}
