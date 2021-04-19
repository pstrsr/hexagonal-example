package de.strasser.peter.hexagonal.persistence.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

  @Bean
  public MongoClient mongoClient() {
    return MongoClients.create("mongodb://admin:changeme@localhost:27017");
  }
}
