package de.strasser.peter.hexagonal.addressvalidation;

import de.strasser.peter.hexagonal.application.customer.port.out.commands.ValidateAddressCommand;


class AddressDoesNotExistExc extends IllegalArgumentException {
    AddressDoesNotExistExc(ValidateAddressCommand validateAddressCommand) {
        super(String.format("Address %s does not exist", validateAddressCommand.toString()));
    }
}
