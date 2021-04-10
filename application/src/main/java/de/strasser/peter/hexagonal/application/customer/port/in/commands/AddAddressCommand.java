package de.strasser.peter.hexagonal.application.customer.port.in.commands;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AddAddressCommand {
    @NotEmpty String type;
    @NotEmpty String street;
    Integer houseNumber;
    @Min(0)
    Integer zipCode;
    @NotEmpty String country;
}
