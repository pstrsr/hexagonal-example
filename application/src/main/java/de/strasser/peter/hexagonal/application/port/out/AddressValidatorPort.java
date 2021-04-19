package de.strasser.peter.hexagonal.application.port.out;

import de.strasser.peter.hexagonal.application.domain.Address;
import de.strasser.peter.hexagonal.application.port.out.commands.ValidateAddressCommand;

public interface AddressValidatorPort {
  Address validate(ValidateAddressCommand validateAddressCommand);
}
