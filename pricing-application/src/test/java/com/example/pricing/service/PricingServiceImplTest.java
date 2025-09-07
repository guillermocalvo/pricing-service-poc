package com.example.pricing.service;

import static java.math.BigDecimal.TEN;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.pricing.domain.MoneyAmount;
import com.example.pricing.domain.Price;
import com.example.pricing.domain.ValidityPeriod;
import com.example.pricing.port.persistence.PricePersistencePort;
import java.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PricingServiceImplTest {

  static final Currency EUR = Currency.getInstance("EUR");

  PricePersistencePort pricePersistenceMock;
  PricingServiceImpl pricingService;

  @BeforeEach
  void setUp() {
    pricePersistenceMock = mock(PricePersistencePort.class);
    pricingService = new PricingServiceImpl(pricePersistenceMock);
  }

  @Test
  void testFound() {
    final var period = new ValidityPeriod(now(), now().plus(7, DAYS));
    final var amount = new MoneyAmount(TEN, EUR);
    final var expected = new Price(123L, 1L, 35455L, 1L, 0, period, amount);
    when(pricePersistenceMock.findPrice(anyLong(), anyLong(), any())).thenReturn(of(expected));
    final var actual = pricingService.findCurrentPrice(1L, 35455L, now().plus(2, DAYS));
    assertThat(actual).contains(expected);
  }

  @Test
  void testNotFound() {
    when(pricePersistenceMock.findPrice(anyLong(), anyLong(), any())).thenReturn(empty());
    final var actual = pricingService.findCurrentPrice(1L, 35455L, now().plus(2, DAYS));
    assertThat(actual).isEmpty();
  }
}
