package com.example.pricing.adapter.query.rest;

import static java.util.Objects.requireNonNull;

import com.example.pricing.adapter.query.rest.dto.ApiErrorResponse;
import com.example.pricing.adapter.query.rest.dto.PriceQueryApiRequest;
import com.example.pricing.adapter.query.rest.dto.PriceQueryApiResponse;
import com.example.pricing.adapter.query.rest.mapper.PriceQueryResponseMapper;
import com.example.pricing.exception.PriceNotFoundException;
import com.example.pricing.port.query.PriceQueryPort;
import com.example.pricing.port.query.dto.PriceQueryRequest;
import com.example.pricing.service.PricingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Implements the price query port as a REST API.
 *
 * @param pricingService The pricing pricingService.
 */
@Slf4j
@RestController
@Tag(name = "price")
public record PricingController(PricingService pricingService) implements PriceQueryPort {

  /**
   * Creates a new instance.
   *
   * @param pricingService The pricing pricingService.
   */
  public PricingController {
    requireNonNull(pricingService);
  }

  /**
   * Handles a price query request.
   *
   * @param brandId The brand ID.
   * @param productId The product ID.
   * @param date The point in time.
   * @return A price query response.
   * @throws PriceNotFoundException If there is no such price for the request.
   */
  @Operation(summary = "Get price", description = "Returns the current retail price for a product")
  @GetMapping("/api/v1/price/{brandId}/{productId}/{date}")
  public PriceQueryApiResponse getPrice(
      @PathVariable("brandId") @Parameter(description = "The brand ID", example = "1") long brandId,
      @PathVariable("productId") @Parameter(description = "The product ID", example = "35455")
          long productId,
      @PathVariable("date")
          @Parameter(description = "The point in time", example = "2020-07-07T12:30:00Z")
          @NotNull
          Instant date)
      throws PriceNotFoundException {
    final PriceQueryRequest request = new PriceQueryApiRequest(brandId, productId, date);
    log.info("Get price {}", request);
    return handle(request);
  }

  /** {@inheritDoc} */
  @Operation(
      summary = "Query price",
      description = "Returns the current retail price for a product",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              content = @Content(schema = @Schema(implementation = PriceQueryApiRequest.class))))
  // TODO: Replace HTTP verb `POST` with `QUERY` when available
  // see: https://httpwg.org/http-extensions/draft-ietf-httpbis-safe-method-w-body.html
  @PostMapping("/api/v1/price/query")
  @Override
  @NotNull
  public PriceQueryApiResponse queryPrice(@NotNull @Valid @RequestBody PriceQueryRequest request)
      throws PriceNotFoundException {
    log.info("Query price {}", request);
    return handle(request);
  }

  PriceQueryApiResponse handle(PriceQueryRequest request) throws PriceNotFoundException {
    return pricingService
        .findCurrentPrice(request.brandId(), request.productId(), request.date())
        .map(PriceQueryResponseMapper.INSTANCE::toDto)
        .orElseThrow(() -> notFound(request));
  }

  /**
   * Turns an exception into an error response
   *
   * @param e The exception.
   * @return An error response.
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(PriceNotFoundException.class)
  public ApiErrorResponse handleNotFoundException(PriceNotFoundException e) {
    log.warn("Price not found (brand: {}, product: {}, date: {})", e.brandId, e.productId, e.date);
    return new ApiErrorResponse(e.getMessage());
  }

  /**
   * Turns an exception into an error response
   *
   * @param e The exception.
   * @return An error response.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ApiErrorResponse handleArgumentMismatchException(MethodArgumentTypeMismatchException e) {
    log.warn("Invalid argument: {}", e.getName(), e);
    return new ApiErrorResponse("Invalid argument: " + e.getName());
  }

  /**
   * Turns an exception into an error response
   *
   * @param e The exception.
   * @return An error response.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ApiErrorResponse handleMessageNotReadableException(HttpMessageNotReadableException e) {
    log.warn("Invalid request", e);
    return new ApiErrorResponse("Invalid request: " + e.getMessage());
  }

  /**
   * Turns a price query request into a "price not found" exception.
   *
   * @param req The price query request.
   * @return A "price not found" exception.
   */
  static PriceNotFoundException notFound(PriceQueryRequest req) {
    return new PriceNotFoundException(req.brandId(), req.productId(), req.date());
  }
}
