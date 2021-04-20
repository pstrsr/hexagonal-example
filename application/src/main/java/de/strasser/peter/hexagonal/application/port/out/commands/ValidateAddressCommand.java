package de.strasser.peter.hexagonal.application.port.out.commands;




public record ValidateAddressCommand (
  String street,
  String city,
  Integer zipCode,
  String country
){}
