package de.strasser.peter.hexagonal.application.customer.service;

import de.strasser.peter.hexagonal.application.customer.domain.Address;
import de.strasser.peter.hexagonal.application.customer.domain.Customer;
import de.strasser.peter.hexagonal.application.customer.mapper.AddAddressMapper;
import de.strasser.peter.hexagonal.application.customer.port.in.AddAddressUseCase;
import de.strasser.peter.hexagonal.application.customer.port.in.commands.AddAddressCommand;
import de.strasser.peter.hexagonal.application.customer.port.out.AddressValidatorAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.LoadCustomerAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.SaveCustomerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class AddressService implements AddAddressUseCase {
    private final SaveCustomerAdapter saveCustomerAdapter;
    private final AddressValidatorAdapter addressValidatorAdapter;
    private final LoadCustomerAdapter loadCustomerAdapter;
    private final AddAddressMapper addAddressMapper;

    @Override
    public void addAddresses(@Min(0) Integer customerId, @Valid @NotEmpty List<AddAddressCommand> addAddressCmds) {
        final Customer customer = loadCustomerAdapter.findById(customerId);

        final Map<Address.AddressType, Address> addresses = new HashMap<>();
        addAddressCmds.forEach(addAddressCommand -> validateAndAddToMap(addresses, addAddressCommand));

        customer.addAddresses(addresses);

        saveCustomerAdapter.upsert(customer);
    }

    private void validateAndAddToMap(Map<Address.AddressType, Address> addresses, AddAddressCommand addAddressCommand) {
        final var addressType = Address.AddressType.fromString(addAddressCommand.getType());
        final var validateAddressCmd = addAddressMapper.toOutCmd(addAddressCommand);
        final var validatedAddress = addressValidatorAdapter.validate(validateAddressCmd);

        addresses.put(addressType, validatedAddress);
    }
}
