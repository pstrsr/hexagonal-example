package de.strasser.peter.hexagonal.addressvalidation;

import de.strasser.peter.hexagonal.application.exception.BusinessException;
import de.strasser.peter.hexagonal.application.port.out.commands.ValidateAddressCommand;

public class InvalidAddressExc extends BusinessException {
  public InvalidAddressExc(ValidateAddressCommand validateAddressCommand) {
    super(
        String.format(
            "Address <%s, %s, %d, %s> does not exist",
            validateAddressCommand.street(),
            validateAddressCommand.city(),
            validateAddressCommand.zipCode(),
            validateAddressCommand.country()));
  }
}
