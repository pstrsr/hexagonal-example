package de.strasser.peter.hexagonal.application.customer.port.out.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ValidateAddressCommand {
  String street;
  Integer houseNumber;
  Integer zipCode;
  String country;
}
