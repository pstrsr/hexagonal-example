package de.strasser.peter.hexagonal.application;

import de.strasser.peter.hexagonal.application.mapper.AddAddressMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.SpyBean;

@Slf4j
@SpringBootApplication
public class TestConfiguration {

  @SpyBean public AddAddressMapper addAddressMapperMock;
}
