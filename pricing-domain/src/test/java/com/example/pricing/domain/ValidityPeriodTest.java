package com.example.pricing.domain;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class ValidityPeriodTest {

  @Test
  void testValid() {
    final Instant from = now();
    final Instant to = from.plus(7, DAYS);
    final ValidityPeriod validityPeriod = new ValidityPeriod(from, to);
    assertThat(validityPeriod)
        .hasFieldOrPropertyWithValue("from", from)
        .hasFieldOrPropertyWithValue("to", to);
  }

  @Test
  void testNullFrom() {
    final Instant from = null;
    final Instant to = now();
    assertThatThrownBy(() -> new ValidityPeriod(from, to)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void testNullTo() {
    final Instant from = now();
    final Instant to = null;
    assertThatThrownBy(() -> new ValidityPeriod(from, to)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void testSameInstant() {
    final Instant from = now();
    final Instant to = from;
    assertThatThrownBy(() -> new ValidityPeriod(from, to))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void testNotBefore() {
    final Instant from = now();
    final Instant to = from.minus(5, DAYS);
    assertThatThrownBy(() -> new ValidityPeriod(from, to))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
