package de.strasser.peter.hexagonal.application.exception;

public class AddressTypeDoesNotExistsExc extends BusinessException {
  public AddressTypeDoesNotExistsExc(String type) {
    super(String.format("Adress of type '%s' does not exist!", type));
  }
}
