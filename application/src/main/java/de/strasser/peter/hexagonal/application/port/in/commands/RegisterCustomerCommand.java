package de.strasser.peter.hexagonal.application.port.in.commands;

import de.strasser.peter.hexagonal.application.validator.SecurePassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;


public record RegisterCustomerCommand (
  @NotEmpty String name,
  @Past LocalDate birthDay,
  @SecurePassword String clearPassword
){}
