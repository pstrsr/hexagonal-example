package de.strasser.peter.hexagonal.persistence.config;

import de.strasser.peter.hexagonal.persistence.repository.CustomerRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = CustomerRepository.class)
public class MongoConfig {
}
