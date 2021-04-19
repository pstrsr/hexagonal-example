package de.strasser.peter.hexagonal.application.exception;

public abstract class BusinessException extends IllegalStateException {
  public BusinessException(String error) {
    super(error);
  }
}
