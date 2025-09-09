package com.example.pricing.adapter.query.rest.dto;

import static java.util.Objects.requireNonNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents an API error.
 *
 * @param error The error message.
 */
@Schema(description = "Pricing API Error")
public record ApiErrorResponse(
    @Schema(description = "Error message", example = "Price not found") String error) {

  /**
   * Creates a new instance.
   *
   * @param error The error message.
   */
  public ApiErrorResponse {
    requireNonNull(error);
  }
}
