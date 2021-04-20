package de.strasser.peter.hexagonal.application.port.in.commands;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public record AddAddressCommand (
  @NotEmpty String type,
  @NotEmpty String street,
  @NotEmpty String city,
  @Min(0) Integer zipCode,
  @NotEmpty String country
){}
