package de.strasser.peter.hexagonal.application.customer.port.out;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;

import java.math.BigInteger;

public interface LoadCustomerAdapter {
    Customer findById(BigInteger id);
}
