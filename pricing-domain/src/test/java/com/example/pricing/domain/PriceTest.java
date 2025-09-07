package com.example.pricing.domain;

import static java.math.BigDecimal.TEN;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Currency;
import org.junit.jupiter.api.Test;

class PriceTest {

  static final Currency USD = Currency.getInstance("USD");

  @Test
  void testValid() {
    final var id = 123L;
    final var brandId = 1L;
    final var productId = 35455L;
    final var priceListId = 1L;
    final var priority = 0;
    final var period = new ValidityPeriod(now(), now().plus(7, DAYS));
    final var amount = new MoneyAmount(TEN, USD);
    final var price = new Price(id, brandId, productId, priceListId, priority, period, amount);
    assertThat(price)
        .hasFieldOrPropertyWithValue("id", id)
        .hasFieldOrPropertyWithValue("brandId", brandId)
        .hasFieldOrPropertyWithValue("productId", productId)
        .hasFieldOrPropertyWithValue("priceListId", priceListId)
        .hasFieldOrPropertyWithValue("priority", priority)
        .hasFieldOrPropertyWithValue("period", period)
        .hasFieldOrPropertyWithValue("amount", amount);
  }

  @Test
  void testNullPeriod() {
    final var id = 123L;
    final var brandId = 1L;
    final var productId = 35455L;
    final var priceListId = 1L;
    final var priority = 0;
    final ValidityPeriod period = null;
    final var amount = new MoneyAmount(TEN, USD);
    assertThatThrownBy(
            () -> new Price(id, brandId, productId, priceListId, priority, period, amount))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void testNullAmount() {
    final var id = 123L;
    final var brandId = 1L;
    final var productId = 35455L;
    final var priceListId = 1L;
    final var priority = 0;
    final var period = new ValidityPeriod(now(), now().plus(7, DAYS));
    final MoneyAmount amount = null;
    assertThatThrownBy(
            () -> new Price(id, brandId, productId, priceListId, priority, period, amount))
        .isInstanceOf(NullPointerException.class);
  }
}
