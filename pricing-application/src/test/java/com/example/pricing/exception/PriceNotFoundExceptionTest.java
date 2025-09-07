package com.example.pricing.exception;

import static java.time.Instant.now;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PriceNotFoundExceptionTest {

  @Test
  void testNewInstance() {
    final var brandId = 1L;
    final var productId = 35455L;
    final var date = now();
    var exception = new PriceNotFoundException(brandId, productId, date);
    assertThat(exception)
        .hasFieldOrPropertyWithValue("message", "Price not found")
        .hasFieldOrPropertyWithValue("brandId", brandId)
        .hasFieldOrPropertyWithValue("productId", productId)
        .hasFieldOrPropertyWithValue("date", date);
  }
}
