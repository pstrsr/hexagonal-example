package de.strasser.peter.hexagonal.application.customer.port.out;

import de.strasser.peter.hexagonal.application.customer.domain.Address;
import de.strasser.peter.hexagonal.application.customer.port.out.commands.ValidateAddressCommand;

public interface AddressValidatorAdapter {
    Address validate(ValidateAddressCommand validateAddressCommand);
}
