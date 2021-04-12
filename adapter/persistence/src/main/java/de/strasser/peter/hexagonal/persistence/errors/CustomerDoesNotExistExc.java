package de.strasser.peter.hexagonal.persistence.errors;

import de.strasser.peter.hexagonal.application.customer.exception.BusinessException;

import java.math.BigInteger;

public class CustomerDoesNotExistExc extends BusinessException {
  public CustomerDoesNotExistExc(BigInteger id) {
    super("Customer with id " + id.toString() + " does not exist!");
  }
}
