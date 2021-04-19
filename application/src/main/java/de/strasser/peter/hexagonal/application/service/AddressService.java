package de.strasser.peter.hexagonal.application.service;

import de.strasser.peter.hexagonal.application.domain.Address;
import de.strasser.peter.hexagonal.application.domain.Customer;
import de.strasser.peter.hexagonal.application.mapper.AddAddressMapper;
import de.strasser.peter.hexagonal.application.port.in.AddAddressUseCase;
import de.strasser.peter.hexagonal.application.port.in.commands.AddAddressCommand;
import de.strasser.peter.hexagonal.application.port.out.AddressValidatorPort;
import de.strasser.peter.hexagonal.application.port.out.LoadCustomerPort;
import de.strasser.peter.hexagonal.application.port.out.SaveCustomerPort;
import de.strasser.peter.hexagonal.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UseCase
@Transactional
@RequiredArgsConstructor
class AddressService implements AddAddressUseCase {
  private final SaveCustomerPort saveCustomerAdapter;
  private final AddressValidatorPort addressValidatorAdapter;
  private final LoadCustomerPort loadCustomerAdapter;
  private final AddAddressMapper addAddressMapper;

  @Override
  public void addAddresses(
      @Min(0) BigInteger customerId, @Valid @NotEmpty List<AddAddressCommand> addAddressCmds) {
    final Customer customer = loadCustomerAdapter.findById(customerId);

    final Map<Address.AddressType, Address> addresses = new HashMap<>();
    addAddressCmds.forEach(addAddressCommand -> validateAndAddToMap(addresses, addAddressCommand));

    customer.addAddresses(addresses);
    saveCustomerAdapter.upsert(customer);
  }

  private void validateAndAddToMap(
      Map<Address.AddressType, Address> addresses, AddAddressCommand addAddressCommand) {
    final var addressType = Address.AddressType.fromString(addAddressCommand.getType());
    final var validateAddressCmd = addAddressMapper.toOutCmd(addAddressCommand);
    final var validatedAddress = addressValidatorAdapter.validate(validateAddressCmd);

    addresses.put(addressType, validatedAddress);
  }
}
