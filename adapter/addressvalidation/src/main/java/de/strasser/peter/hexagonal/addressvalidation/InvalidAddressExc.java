package de.strasser.peter.hexagonal.addressvalidation;

import de.strasser.peter.hexagonal.application.customer.exception.BusinessException;
import de.strasser.peter.hexagonal.application.customer.port.out.commands.ValidateAddressCommand;

public class InvalidAddressExc extends BusinessException {
  public InvalidAddressExc(ValidateAddressCommand validateAddressCommand) {
    super(
        String.format(
            "Address <%s, %d, %d, %s> does not exist",
            validateAddressCommand.getStreet(),
            validateAddressCommand.getHouseNumber(),
            validateAddressCommand.getZipCode(),
            validateAddressCommand.getCountry()));
  }
}
