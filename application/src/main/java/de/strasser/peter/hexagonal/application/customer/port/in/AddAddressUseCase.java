package de.strasser.peter.hexagonal.application.customer.port.in;

import de.strasser.peter.hexagonal.application.customer.port.in.commands.AddAddressCommand;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.util.List;

public interface AddAddressUseCase {
  void addAddresses(
      @Min(0) BigInteger customerId, @Valid @NotEmpty List<AddAddressCommand> addresses);
}
