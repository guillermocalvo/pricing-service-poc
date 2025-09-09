package com.example.pricing.adapter.query.rest;

import static java.math.BigDecimal.TEN;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import com.example.pricing.adapter.query.rest.dto.PriceQueryApiRequest;
import com.example.pricing.domain.MoneyAmount;
import com.example.pricing.domain.Price;
import com.example.pricing.domain.ValidityPeriod;
import com.example.pricing.exception.PriceNotFoundException;
import com.example.pricing.service.PricingService;
import java.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

class PricingControllerTest {

  static final Currency EUR = Currency.getInstance("EUR");

  PricingService pricingServiceMock;
  PricingController pricingController;

  @BeforeEach
  void setUp() {
    pricingServiceMock = mock(PricingService.class);
    pricingController = new PricingController(pricingServiceMock);
  }

  @Test
  void testGetPrice() throws PriceNotFoundException {
    final var period = new ValidityPeriod(now(), now().plus(7, DAYS));
    final var amount = new MoneyAmount(TEN, EUR);
    final var expected = new Price(123L, 1L, 35455L, 1L, 0, period, amount);
    when(pricingServiceMock.findCurrentPrice(anyLong(), anyLong(), any())).thenReturn(of(expected));
    final var response = pricingController.getPrice(1L, 35455L, now().plus(2, DAYS));
    assertThat(response)
        .hasFieldOrPropertyWithValue("brandId", expected.brandId())
        .hasFieldOrPropertyWithValue("productId", expected.productId())
        .hasFieldOrPropertyWithValue("priceListId", expected.priceListId())
        .hasFieldOrPropertyWithValue("startDate", expected.period().from())
        .hasFieldOrPropertyWithValue("endDate", expected.period().to())
        .hasFieldOrPropertyWithValue("price", expected.amount().value())
        .hasFieldOrPropertyWithValue("currency", expected.amount().currency());
  }

  @Test
  void testQueryPrice() throws PriceNotFoundException {
    final var period = new ValidityPeriod(now(), now().plus(7, DAYS));
    final var amount = new MoneyAmount(TEN, EUR);
    final var expected = new Price(123L, 1L, 35455L, 1L, 0, period, amount);
    when(pricingServiceMock.findCurrentPrice(anyLong(), anyLong(), any())).thenReturn(of(expected));
    final var request = new PriceQueryApiRequest(1L, 35455L, now().plus(2, DAYS));
    final var response = pricingController.queryPrice(request);
    assertThat(response)
        .hasFieldOrPropertyWithValue("brandId", expected.brandId())
        .hasFieldOrPropertyWithValue("productId", expected.productId())
        .hasFieldOrPropertyWithValue("priceListId", expected.priceListId())
        .hasFieldOrPropertyWithValue("startDate", expected.period().from())
        .hasFieldOrPropertyWithValue("endDate", expected.period().to())
        .hasFieldOrPropertyWithValue("price", expected.amount().value())
        .hasFieldOrPropertyWithValue("currency", expected.amount().currency());
  }

  @Test
  void testNotFound() {
    when(pricingServiceMock.findCurrentPrice(anyLong(), anyLong(), any())).thenReturn(empty());
    assertThatThrownBy(() -> pricingController.getPrice(1L, 35455L, now().plus(2, DAYS)))
        .isInstanceOf(PriceNotFoundException.class);
  }

  @Test
  void testNotFoundResponse() {
    final var exception = new PriceNotFoundException(1L, 35455L, now());
    final var response = pricingController.handleNotFoundException(exception);
    assertThat(response).hasFieldOrPropertyWithValue("error", "Price not found");
  }

  @Test
  void testArgumentMismatchResponse() {
    final var exception = mock(MethodArgumentTypeMismatchException.class);
    when(exception.getName()).thenReturn("brandId");
    final var response = pricingController.handleArgumentMismatchException(exception);
    assertThat(response).hasFieldOrPropertyWithValue("error", "Invalid argument: brandId");
  }

  @Test
  void testMessageNotReadableResponse() {
    final var exception = mock(HttpMessageNotReadableException.class);
    when(exception.getMessage()).thenReturn("testing");
    final var response = pricingController.handleMessageNotReadableException(exception);
    assertThat(response).hasFieldOrPropertyWithValue("error", "Invalid request: testing");
  }
}
