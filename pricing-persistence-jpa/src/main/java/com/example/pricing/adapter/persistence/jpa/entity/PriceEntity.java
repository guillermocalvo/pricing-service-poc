package com.example.pricing.adapter.persistence.jpa.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import lombok.Getter;
import lombok.Setter;

/** Represents a price row in the database. */
@Entity
@Table(name = "PRICES")
@Getter
@Setter
public class PriceEntity {

  /** Creates a new instance. */
  public PriceEntity() {
    // Does nothing
  }

  /** The price ID. */
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID")
  Long id;

  /** The brand ID. */
  @Column(name = "BRAND_ID", nullable = false)
  Long brandId;

  /** The start date. */
  @Column(name = "START_DATE", nullable = false)
  Instant startDate;

  /** The end date. */
  @Column(name = "END_DATE", nullable = false)
  public Instant endDate;

  /** The price list ID. */
  @Column(name = "PRICE_LIST", nullable = false)
  long priceListId;

  /** The product ID. */
  @Column(name = "PRODUCT_ID", nullable = false)
  long productId;

  /** The priority (in case of overlap) */
  @Column(name = "PRIORITY", nullable = false)
  int priority;

  /** The retail price. */
  @Column(name = "PRICE", nullable = false, precision = 19, scale = 4)
  BigDecimal price;

  /** The currency. */
  @Column(name = "CURR", nullable = false)
  Currency currency;
}
