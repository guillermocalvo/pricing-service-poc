package com.example.pricing.adapter.persistence.jpa;

import com.example.pricing.adapter.persistence.jpa.mapper.PriceEntityMapper;
import com.example.pricing.domain.Price;
import com.example.pricing.port.persistence.PricePersistencePort;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Handles price persistence using JPA.
 *
 * @param repository The JPA repository.
 */
@Slf4j
@Service
public record PricePersistence(PriceJpaRepository repository) implements PricePersistencePort {

  /**
   * Finds the price of a product at a specific point in time.
   *
   * @param brandId The brand ID.
   * @param productId The product ID.
   * @param date The point in time.
   * @return An optional price.
   */
  @Override
  @NotNull
  public Optional<Price> findPrice(long brandId, long productId, @NotNull Instant date) {
    log.info(
        "Finding retail price from database (brand: {}, product: {}, date: {})",
        brandId,
        productId,
        date);
    return repository
        .findTopPrice(brandId, productId, date)
        .map(PriceEntityMapper.INSTANCE::toDomain);
  }
}
