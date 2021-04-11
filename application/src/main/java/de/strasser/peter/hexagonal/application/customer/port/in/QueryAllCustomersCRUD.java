package de.strasser.peter.hexagonal.application.customer.port.in;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;

import java.util.List;

public interface QueryAllCustomersCRUD {
  List<Customer> getAll();
}
