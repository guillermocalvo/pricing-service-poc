package com.example.pricing.domain;

import static java.util.Objects.requireNonNull;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Value object that represents an amount of money.
 *
 * @param value The numerical value.
 * @param currency The currency.
 */
public record MoneyAmount(@NotNull BigDecimal value, @NotNull Currency currency) {

  /**
   * Creates a new instance.
   *
   * @param value The numerical value.
   * @param currency The currency.
   */
  public MoneyAmount {
    requireNonNull(value);
    requireNonNull(currency);
    if (value.signum() < 0) {
      throw new IllegalArgumentException("value can't be negative");
    }
  }
}
