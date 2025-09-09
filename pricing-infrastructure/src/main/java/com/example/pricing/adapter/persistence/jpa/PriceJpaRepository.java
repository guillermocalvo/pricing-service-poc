package com.example.pricing.adapter.persistence.jpa;

import com.example.pricing.adapter.persistence.jpa.entity.PriceEntity;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Handles the price repository. */
@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

  /**
   * Finds the appropriate retail price of a given product at a specific point in time.
   *
   * @param brandId The brand ID.
   * @param productId The product ID.
   * @param date The point in time.
   * @return An optional price.
   */
  @Query(
      "FROM PriceEntity p WHERE p.brandId = :brandId AND p.productId = :productId AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC LIMIT 1")
  Optional<PriceEntity> findTopPrice(
      @Param("brandId") long brandId,
      @Param("productId") long productId,
      @Param("date") Instant date);
}
