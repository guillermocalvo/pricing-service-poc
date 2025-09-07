package com.example.pricing.port.query;

import com.example.pricing.exception.PriceNotFoundException;
import com.example.pricing.port.query.dto.PriceQueryRequest;
import com.example.pricing.port.query.dto.PriceQueryResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/** Handles price queries. */
public interface PriceQueryPort {

  /**
   * Handles a price query request.
   *
   * @param request The price query request.
   * @return A price query response.
   * @throws PriceNotFoundException If there is no such price for the request.
   */
  @NotNull
  PriceQueryResponse queryPrice(@NotNull @Valid PriceQueryRequest request)
      throws PriceNotFoundException;
}
