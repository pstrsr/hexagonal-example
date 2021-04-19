package de.strasser.peter.hexagonal.application.port.out;

import de.strasser.peter.hexagonal.application.domain.Customer;

import java.math.BigInteger;

public interface LoadCustomerPort {
  Customer findById(BigInteger id);
}
