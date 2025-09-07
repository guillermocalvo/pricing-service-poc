package com.example.pricing.domain;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Currency;
import org.junit.jupiter.api.Test;

class MoneyAmountTest {

  static final Currency EUR = Currency.getInstance("EUR");

  @Test
  void testValid() {
    final BigDecimal value = TEN;
    final Currency currency = EUR;
    final MoneyAmount moneyAmountTest = new MoneyAmount(value, currency);
    assertThat(moneyAmountTest)
        .hasFieldOrPropertyWithValue("value", value)
        .hasFieldOrPropertyWithValue("currency", currency);
  }

  @Test
  void testZero() {
    final BigDecimal value = ZERO;
    final Currency currency = EUR;
    final MoneyAmount moneyAmountTest = new MoneyAmount(value, currency);
    assertThat(moneyAmountTest)
        .hasFieldOrPropertyWithValue("value", value)
        .hasFieldOrPropertyWithValue("currency", currency);
  }

  @Test
  void testNegative() {
    final BigDecimal value = TEN.negate();
    final Currency currency = EUR;
    assertThatThrownBy(() -> new MoneyAmount(value, currency))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void testNullValue() {
    final BigDecimal value = null;
    final Currency currency = EUR;
    assertThatThrownBy(() -> new MoneyAmount(value, currency))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void testNullTo() {
    final BigDecimal value = TEN;
    final Currency currency = null;
    assertThatThrownBy(() -> new MoneyAmount(value, currency))
        .isInstanceOf(NullPointerException.class);
  }
}
