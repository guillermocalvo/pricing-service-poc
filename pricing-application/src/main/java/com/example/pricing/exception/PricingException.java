package com.example.pricing.exception;

/** Base class for all pricing-related errors. */
public abstract class PricingException extends Exception {

  /**
   * Creates a new instance.
   *
   * @param message The error message.
   */
  protected PricingException(String message) {
    super(message);
  }
}
