package de.strasser.peter.hexagonal.application.customer.port.out.commands;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ValidateAddressCommand {
    String street;
    int houseNumber;
    int zipCode;
    String country;
}
