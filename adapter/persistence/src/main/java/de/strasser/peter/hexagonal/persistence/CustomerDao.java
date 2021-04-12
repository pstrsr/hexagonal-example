package de.strasser.peter.hexagonal.persistence;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;
import de.strasser.peter.hexagonal.application.customer.port.in.QueryAllCustomersCRUD;
import de.strasser.peter.hexagonal.application.customer.port.out.LoadCustomerAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.SaveCustomerAdapter;
import de.strasser.peter.hexagonal.persistence.errors.CustomerDoesNotExistExc;
import de.strasser.peter.hexagonal.persistence.mapper.CustomerMapper;
import de.strasser.peter.hexagonal.persistence.model.CustomerEntity;
import de.strasser.peter.hexagonal.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerDao
    implements SaveCustomerAdapter, LoadCustomerAdapter, QueryAllCustomersCRUD {
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  @Override
  public void upsert(Customer customer) {
    customerRepository.save(customerMapper.toDbEntity(customer));
  }

  @Override
  public Customer findById(BigInteger id) {
    final CustomerEntity customerEntity =
        customerRepository.findById(id).orElseThrow(() -> new CustomerDoesNotExistExc(id));

    return customerMapper.toDomain(customerEntity);
  }

  @Override
  public List<Customer> getAll() {
    return customerMapper.toDomain(customerRepository.findAll());
  }
}
