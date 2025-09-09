package com.example.pricing.adapter.query.rest.dto;

import static java.math.BigDecimal.TEN;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import org.junit.jupiter.api.Test;

class PriceQueryApiResponseTest {

  static final Currency USD = Currency.getInstance("USD");

  @Test
  void testValid() {
    final var brandId = 1L;
    final var productId = 35455L;
    final var priceListId = 1L;
    final var startDate = now();
    final var endDate = now().plus(7, DAYS);
    final var price = TEN;
    final var currency = USD;
    final var response =
        new PriceQueryApiResponse(
            brandId, productId, priceListId, startDate, endDate, price, currency);
    assertThat(response)
        .hasFieldOrPropertyWithValue("brandId", brandId)
        .hasFieldOrPropertyWithValue("productId", productId)
        .hasFieldOrPropertyWithValue("priceListId", priceListId)
        .hasFieldOrPropertyWithValue("startDate", startDate)
        .hasFieldOrPropertyWithValue("endDate", endDate)
        .hasFieldOrPropertyWithValue("price", price)
        .hasFieldOrPropertyWithValue("currency", currency);
  }

  @Test
  void testNullStartDate() {
    final var brandId = 1L;
    final var productId = 35455L;
    final var priceListId = 1L;
    final Instant startDate = null;
    final var endDate = now();
    final var price = TEN;
    final var currency = USD;
    assertThatThrownBy(
            () ->
                new PriceQueryApiResponse(
                    brandId, productId, priceListId, startDate, endDate, price, currency))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void testNullEndDate() {
    final var brandId = 1L;
    final var productId = 35455L;
    final var priceListId = 1L;
    final var startDate = now();
    final Instant endDate = null;
    final var price = TEN;
    final var currency = USD;
    assertThatThrownBy(
            () ->
                new PriceQueryApiResponse(
                    brandId, productId, priceListId, startDate, endDate, price, currency))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void testNullPrice() {
    final long brandId = 1L;
    final long productId = 35455L;
    final long priceListId = 1L;
    final Instant startDate = now();
    final Instant endDate = now().plus(7, DAYS);
    final BigDecimal price = null;
    final Currency currency = USD;
    assertThatThrownBy(
            () ->
                new PriceQueryApiResponse(
                    brandId, productId, priceListId, startDate, endDate, price, currency))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void testNullCurrency() {
    final var brandId = 1L;
    final var productId = 35455L;
    final var priceListId = 1L;
    final var startDate = now();
    final var endDate = now().plus(7, DAYS);
    final var price = TEN;
    final Currency currency = null;
    assertThatThrownBy(
            () ->
                new PriceQueryApiResponse(
                    brandId, productId, priceListId, startDate, endDate, price, currency))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void testStartDateNotBeforeEndDate() {
    final var brandId = 1L;
    final var productId = 35455L;
    final var priceListId = 1L;
    final var startDate = now();
    final var endDate = now().minus(7, DAYS);
    final var price = TEN;
    final var currency = USD;
    assertThatThrownBy(
            () ->
                new PriceQueryApiResponse(
                    brandId, productId, priceListId, startDate, endDate, price, currency))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void testNegativePrice() {
    final var brandId = 1L;
    final var productId = 35455L;
    final var priceListId = 1L;
    final var startDate = now();
    final var endDate = now().plus(7, DAYS);
    final var price = TEN.negate();
    final var currency = USD;
    assertThatThrownBy(
            () ->
                new PriceQueryApiResponse(
                    brandId, productId, priceListId, startDate, endDate, price, currency))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
