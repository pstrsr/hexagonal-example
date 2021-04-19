package de.strasser.peter.hexagonal.application.port.in;

import de.strasser.peter.hexagonal.application.domain.Customer;

import java.util.List;

public interface QueryAllCustomersCRUD {
  List<Customer> getAll();
}
