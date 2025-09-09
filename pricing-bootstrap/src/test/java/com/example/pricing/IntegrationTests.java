package com.example.pricing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.pricing.adapter.query.rest.dto.ApiErrorResponse;
import com.example.pricing.adapter.query.rest.dto.PriceQueryApiResponse;
import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class IntegrationTests {

  static final HttpStatusCode OK = HttpStatusCode.valueOf(200);
  static final HttpStatusCode NOT_FOUND = HttpStatusCode.valueOf(404);
  static final HttpStatusCode BAD_REQUEST = HttpStatusCode.valueOf(400);

  @Autowired TestRestTemplate rest;

  @Test
  void contextLoads() {
    // Checks that the application context loads successfully
  }

  @Test
  void testGetPrice() {
    final var url = "/api/v1/price?brandId=1&productId=35455&date=2020-07-07T12:30:00Z";
    assertThat(rest.getForObject(url, PriceQueryApiResponse.class))
        .hasFieldOrPropertyWithValue("price", new BigDecimal("38.9500"))
        .hasFieldOrPropertyWithValue("priceListId", 4L)
        .hasFieldOrPropertyWithValue("startDate", Instant.parse("2020-06-15T16:00:00Z"))
        .hasFieldOrPropertyWithValue("endDate", Instant.parse("2020-12-31T23:59:59Z"));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/test-batch.csv", numLinesToSkip = 1)
  void testGetPrices(
      long brandId,
      long productId,
      Instant date,
      BigDecimal expectedPrice,
      long expectedPriceListId,
      Instant expectedStartDate,
      Instant expectedEndDate) {
    final var url =
        "/api/v1/price?brandId=" + brandId + "&productId=" + productId + "&date=" + date;
    assertThat(rest.getForEntity(url, PriceQueryApiResponse.class))
        .hasFieldOrPropertyWithValue("statusCode", OK)
        .extracting(ResponseEntity::getBody)
        .hasFieldOrPropertyWithValue("price", expectedPrice)
        .hasFieldOrPropertyWithValue("priceListId", expectedPriceListId)
        .hasFieldOrPropertyWithValue("startDate", expectedStartDate)
        .hasFieldOrPropertyWithValue("endDate", expectedEndDate);
  }

  @Test
  void testNotFound() {
    final var url = "/api/v1/price?brandId=1&productId=35455&date=1980-07-07T12:30:00Z";
    assertThat(rest.getForEntity(url, ApiErrorResponse.class))
        .hasFieldOrPropertyWithValue("statusCode", NOT_FOUND)
        .extracting(ResponseEntity::getBody)
        .hasFieldOrPropertyWithValue("error", "Price not found");
  }

  @Test
  void testIllegalDate() {
    final var url = "/api/v1/price?brandId=1&productId=35455&date=whoops";
    assertThat(rest.getForEntity(url, ApiErrorResponse.class))
        .hasFieldOrPropertyWithValue("statusCode", BAD_REQUEST)
        .extracting(ResponseEntity::getBody)
        .hasFieldOrPropertyWithValue("error", "Invalid argument: date");
  }
}
