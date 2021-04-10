package de.strasser.peter.hexagonal.persistence;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;
import de.strasser.peter.hexagonal.application.customer.port.out.LoadCustomerAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.SaveCustomerAdapter;
import de.strasser.peter.hexagonal.persistence.model.CustomerEntity;
import de.strasser.peter.hexagonal.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerDao implements SaveCustomerAdapter, LoadCustomerAdapter {
    private final CustomerRepository customerRepository;

    @Override
    public void upsert(Customer customer) {
        log.info("saving customer");
    }

    @Override
    public Customer findById(Integer id) {
        Optional<CustomerEntity> byId = customerRepository.findById(id);
        return Customer.createCustomer(id,
                "max",
                "mustermann",
                LocalDate.of(1980, 1, 1),
                new HashMap<>(),
                true);
    }
}
