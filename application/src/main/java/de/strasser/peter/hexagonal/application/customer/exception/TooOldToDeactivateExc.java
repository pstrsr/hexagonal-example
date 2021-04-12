package de.strasser.peter.hexagonal.application.customer.exception;

public class TooOldToDeactivateExc extends BusinessException {
  public TooOldToDeactivateExc(int age) {
    super("Customer is too old to be deactivated. Expected < 50, Actual: " + age);
  }
}
