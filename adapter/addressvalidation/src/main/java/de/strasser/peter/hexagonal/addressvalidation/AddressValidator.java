package de.strasser.peter.hexagonal.addressvalidation;

import de.strasser.peter.hexagonal.application.domain.Address;
import de.strasser.peter.hexagonal.application.port.out.AddressValidatorPort;
import de.strasser.peter.hexagonal.application.port.out.commands.ValidateAddressCommand;
import de.strasser.peter.hexagonal.common.Adapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
class AddressValidator implements AddressValidatorPort {

  @Override
  public Address validate(ValidateAddressCommand validateAddressCommand) throws InvalidAddressExc {
    // This could be some call to a 3rd party to validate this address.
    if (validateAddressCommand.street().equalsIgnoreCase("parkring")) {
      log.info("Address is made up.");
      throw new InvalidAddressExc(validateAddressCommand);
    }

    return new Address(
        validateAddressCommand.street(),
        validateAddressCommand.city(),
        validateAddressCommand.zipCode(),
        validateAddressCommand.country());
  }
}
