package de.strasser.peter.hexagonal.application.customer.port.out;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;

public interface SaveCustomerAdapter {
    void upsert(Customer customer);
}
