package de.strasser.peter.hexagonal.application.customer.domain;

import de.strasser.peter.hexagonal.application.customer.exception.TooOldToDeactivateExc;
import de.strasser.peter.hexagonal.application.customer.exception.TooYoungExc;
import de.strasser.peter.hexagonal.application.customer.mapper.AddAddressMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {


  @Test
  public void should_CreateNewCustomer() {
    final var customer = Customer.newCustomer("name", "pw", LocalDate.of(1980, 1, 1));

    assertNotNull(customer);
    assertNull(customer.getId());
  }

  @Test
  public void should_ThrowTooYoungErr_When_CreatingNewCustomer() {
    assertThrows(
        TooYoungExc.class, () -> Customer.newCustomer("name", "pw", LocalDate.of(2010, 1, 1)));
  }

  @Test
  public void should_ActivateCustomer_When_AddingDefaultAddress() {
    final Customer customer = Customer.newCustomer("name", "pw", LocalDate.of(1980, 1, 1));
    final Address address = new Address("Parkring", 59, 85748, "Germany");

    customer.addAddresses(Collections.singletonMap(Address.AddressType.DEFAULT, address));

    assertTrue(customer.isActive());
  }

  @Test
  public void should_NotActivateCustomer_When_AddingBillingAddress() {
    final Customer customer = Customer.newCustomer("name", "pw", LocalDate.of(1980, 1, 1));
    final Address address = new Address("Parkring", 59, 85748, "Germany");

    customer.addAddresses(Collections.singletonMap(Address.AddressType.BILLING, address));

    assertFalse(customer.isActive());
  }

  @Test
  public void should_DeactivateCustomer() {
    final Customer customer = Customer.newCustomer("name", "pw", LocalDate.of(1980, 1, 1));
    customer.deactivate();

    assertFalse(customer.isActive());
  }

  @Test
  public void should_ThrowTooOldErr_When_DeactivatingOldCustomer(){
    final Customer customer = Customer.newCustomer("name", "pw", LocalDate.of(1950, 1, 1));
    assertThrows(TooOldToDeactivateExc.class, customer::deactivate);
  }


}
