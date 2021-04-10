package de.strasser.peter.hexagonal.application.customer.exception;

public class AddressTypeDoesNotExistsExc extends IllegalArgumentException {
    public AddressTypeDoesNotExistsExc(String type) {
        super(String.format("Adress of type '%s' does not exist!", type));
    }
}
