package de.strasser.peter.hexagonal.persistence;

import de.strasser.peter.hexagonal.application.domain.Customer;
import de.strasser.peter.hexagonal.persistence.mapper.CustomerMapperImpl;
import de.strasser.peter.hexagonal.persistence.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@Import({CustomerDao.class, CustomerMapperImpl.class})
class CustomerDaoTest {
  @Autowired private CustomerDao customerDao;

  @Autowired private CustomerRepository customerRepository;

  @AfterEach
  void cleanUp() {
    this.customerRepository.deleteAll();
  }

  @Test
  @Timeout(5)
  void should_ContainOneCustomer_When_CallingInsertMethod() {
    var customer = Customer.newCustomer("hans", "passwsord", LocalDate.of(1980, 1, 1));
    this.customerDao.upsert(customer);

    assertEquals(this.customerRepository.findAll().size(), 1);
  }
}
