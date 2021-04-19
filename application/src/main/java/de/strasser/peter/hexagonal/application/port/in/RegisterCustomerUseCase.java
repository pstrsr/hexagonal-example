package de.strasser.peter.hexagonal.application.port.in;

import de.strasser.peter.hexagonal.application.port.in.commands.RegisterCustomerCommand;

import javax.validation.Valid;

public interface RegisterCustomerUseCase {
  void register(@Valid RegisterCustomerCommand registerCustomerCommand);
}
