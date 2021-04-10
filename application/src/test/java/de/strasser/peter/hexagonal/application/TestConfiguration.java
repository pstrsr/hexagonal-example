package de.strasser.peter.hexagonal.application;


import de.strasser.peter.hexagonal.application.customer.mapper.AddAddressMapper;
import de.strasser.peter.hexagonal.application.customer.port.out.AddressValidatorAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.LoadCustomerAdapter;
import de.strasser.peter.hexagonal.application.customer.port.out.SaveCustomerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
public class TestConfiguration {
    @Bean
    public PasswordEncoder pwEncoder() {
        return new BCryptPasswordEncoder();
    }



}
