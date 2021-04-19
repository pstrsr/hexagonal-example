package de.strasser.peter.hexagonal.application.port.out;

import de.strasser.peter.hexagonal.application.domain.Customer;

public interface SaveCustomerPort {
  void upsert(Customer customer);
}
