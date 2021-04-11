package de.strasser.peter.hexagonal.persistence.repository;

import de.strasser.peter.hexagonal.persistence.model.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, Integer> {
}
