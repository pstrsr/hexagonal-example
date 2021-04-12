package de.strasser.peter.hexagonal.application.customer.service;

import de.strasser.peter.hexagonal.application.customer.domain.Address;
import de.strasser.peter.hexagonal.application.customer.domain.Customer;
import de.strasser.peter.hexagonal.application.customer.mapper.AddAddressMapper;
import de.strasser.peter.hexagonal.application.customer.port.in.commands.AddAddressCommand;
import de.strasser.peter.hexagonal.application.customer.port.out.AddressValidatorAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.LoadCustomerAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.SaveCustomerAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.commands.ValidateAddressCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest
class AddressServiceTest {

  @Autowired private AddressService sut;
  @MockBean private SaveCustomerAdapter saveCustomerAdapterMock;
  @MockBean private AddressValidatorAdapter addressValidatorAdapterMock;
  @MockBean private LoadCustomerAdapter loadCustomerAdapterMock;
  @SpyBean private AddAddressMapper addAddressMapperMock;

  @Test
  public void should_AddAddress() {
    // GIVEN
    final var customerId = BigInteger.valueOf(13);
    final var name = "hans";
    final var birthday = LocalDate.of(1980, 1, 1);
    final var passwd = "secretPw";

    given(loadCustomerAdapterMock.findById(any()))
        .willReturn(Customer.createCustomer(customerId, name, passwd, birthday, null, false));

    final String street = "Parkring";
    final int houseNumber = 57;
    final int zipCode = 85748;
    final String country = "Germany";

    final Address validatedAddress = new Address(street, houseNumber, zipCode, country);
    final ValidateAddressCommand validateAddressCmd =
        new ValidateAddressCommand(street, houseNumber, zipCode, country);

    given(addressValidatorAdapterMock.validate(validateAddressCmd)).willReturn(validatedAddress);

    final List<AddAddressCommand> addAddressCmds =
        List.of(new AddAddressCommand("billing", street, houseNumber, zipCode, country));

    sut.addAddresses(customerId, addAddressCmds);

    final Customer customerToBeSaved =
        Customer.createCustomer(
            customerId,
            name,
            passwd,
            birthday,
            Map.of(Address.AddressType.BILLING, validatedAddress),
            false);
    then(saveCustomerAdapterMock).should().upsert(eq(customerToBeSaved));
  }

  @Test
  public void should_AddAddressAndActivateCustomer_When_ProvidedDefaultAddress() {
    // GIVEN
    final var customerId = BigInteger.valueOf(13);
    final var name = "hans";
    final var birthday = LocalDate.of(1980, 1, 1);
    final var passwd = "secretPw";

    given(loadCustomerAdapterMock.findById(any()))
        .willReturn(Customer.createCustomer(customerId, name, passwd, birthday, null, false));

    final String street = "Parkring";
    final int houseNumber = 57;
    final int zipCode = 85748;
    final String country = "Germany";

    final Address validatedAddress = new Address(street, houseNumber, zipCode, country);
    final ValidateAddressCommand validateAddressCmd =
        new ValidateAddressCommand(street, houseNumber, zipCode, country);

    given(addressValidatorAdapterMock.validate(validateAddressCmd)).willReturn(validatedAddress);

    final List<AddAddressCommand> addAddressCmds =
        List.of(new AddAddressCommand("default", street, houseNumber, zipCode, country));

    sut.addAddresses(customerId, addAddressCmds);

    final Customer customerToBeSaved =
        Customer.createCustomer(
            customerId,
            name,
            passwd,
            birthday,
            Map.of(Address.AddressType.DEFAULT, validatedAddress),
            true);
    then(saveCustomerAdapterMock).should().upsert(eq(customerToBeSaved));
  }

  @Test
  public void should_ThrowError_When_ProvidingInvalidAddress() {
    // GIVEN
    final var customerId = BigInteger.valueOf(13);
    final var name = "hans";
    final var birthday = LocalDate.of(1980, 1, 1);
    final var passwd = "secretPw";

    given(loadCustomerAdapterMock.findById(any()))
        .willReturn(Customer.createCustomer(customerId, name, passwd, birthday, null, false));

    final String street = "Parkring";
    final int houseNumber = 57;
    final int zipCode = 85748;
    final String country = "Germany";

    final ValidateAddressCommand validateAddressCmd =
        new ValidateAddressCommand(street, houseNumber, zipCode, country);

    given(addressValidatorAdapterMock.validate(validateAddressCmd))
        .willThrow(new IllegalArgumentException("invalid adress"));

    final List<AddAddressCommand> addAddressCmds =
        List.of(new AddAddressCommand("billing", street, houseNumber, zipCode, country));

    assertThrows(
        IllegalArgumentException.class, () -> sut.addAddresses(customerId, addAddressCmds));
  }
}
