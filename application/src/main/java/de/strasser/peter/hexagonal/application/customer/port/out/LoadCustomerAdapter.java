package de.strasser.peter.hexagonal.application.customer.port.out;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;

public interface LoadCustomerAdapter {
    Customer findById(Integer id);
}
