package de.strasser.peter.hexagonal.persistence;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;
import de.strasser.peter.hexagonal.application.customer.port.in.QueryAllCustomersCRUD;
import de.strasser.peter.hexagonal.application.customer.port.out.LoadCustomerAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.SaveCustomerAdapter;
import de.strasser.peter.hexagonal.persistence.mapper.CustomerMapper;
import de.strasser.peter.hexagonal.persistence.model.CustomerEntity;
import de.strasser.peter.hexagonal.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerDao implements SaveCustomerAdapter, LoadCustomerAdapter, QueryAllCustomersCRUD {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void upsert(Customer customer) {
        log.info("saving customer");
        customerRepository.save(customerMapper.toDbEntity(customer));
    }

    @Override
    public Customer findById(BigInteger id) {
        Optional<CustomerEntity> byId = customerRepository.findById(id);
        return Customer.createCustomer(id,
                "max",
                "mustermann",
                LocalDate.of(1980, 1, 1),
                new HashMap<>(),
                true);
    }

    @Override
    public List<Customer> getAll() {
        return customerMapper.toDomain(customerRepository.findAll());
    }
}
