package de.strasser.peter.hexagonal.application.customer.port.out;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;

public interface SaveCustomerPort {
  void upsert(Customer customer);
}
