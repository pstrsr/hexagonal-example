package de.strasser.peter.hexagonal.application.customer.exception;

public abstract class BusinessException extends IllegalStateException {
  public BusinessException(String error) {
    super(error);
  }
}
