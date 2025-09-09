package com.example.pricing.adapter.persistence.jpa;

import static java.math.BigDecimal.TEN;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.example.pricing.adapter.persistence.jpa.entity.PriceEntity;
import java.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PricePersistenceTest {

  static final Currency EUR = Currency.getInstance("EUR");

  PriceJpaRepository priceJpaRepositoryMock;
  PricePersistence pricePersistence;

  @BeforeEach
  void setUp() {
    priceJpaRepositoryMock = mock(PriceJpaRepository.class);
    pricePersistence = new PricePersistence(priceJpaRepositoryMock);
  }

  @Test
  void testFound() {
    final var expected = new PriceEntity();
    expected.setId(123L);
    expected.setBrandId(1L);
    expected.setStartDate(now());
    expected.setEndDate(now().plus(7, DAYS));
    expected.setPriceListId(1L);
    expected.setProductId(35455L);
    expected.setPriority(0);
    expected.setPrice(TEN);
    expected.setCurrency(EUR);
    when(priceJpaRepositoryMock.findTopPrice(anyLong(), anyLong(), any())).thenReturn(of(expected));
    final var actual = pricePersistence.findPrice(1L, 35455L, now());
    assertThat(actual)
        .hasValueSatisfying(
            p ->
                assertThat(p)
                    .hasFieldOrPropertyWithValue("id", expected.getId())
                    .hasFieldOrPropertyWithValue("brandId", expected.getBrandId())
                    .hasFieldOrPropertyWithValue("period.from", expected.getStartDate())
                    .hasFieldOrPropertyWithValue("period.to", expected.getEndDate())
                    .hasFieldOrPropertyWithValue("priceListId", expected.getPriceListId())
                    .hasFieldOrPropertyWithValue("productId", expected.getProductId())
                    .hasFieldOrPropertyWithValue("priority", expected.getPriority())
                    .hasFieldOrPropertyWithValue("amount.value", expected.getPrice())
                    .hasFieldOrPropertyWithValue("amount.currency", expected.getCurrency()));
  }

  @Test
  void testNotFound() {
    when(priceJpaRepositoryMock.findTopPrice(anyLong(), anyLong(), any())).thenReturn(empty());
    final var actual = pricePersistence.findPrice(1L, 35455L, now());
    assertThat(actual).isEmpty();
  }
}
