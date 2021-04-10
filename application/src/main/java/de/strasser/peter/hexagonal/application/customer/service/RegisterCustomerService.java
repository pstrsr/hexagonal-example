package de.strasser.peter.hexagonal.application.customer.service;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;
import de.strasser.peter.hexagonal.application.customer.port.in.RegisterCustomerUseCase;
import de.strasser.peter.hexagonal.application.customer.port.in.commands.RegisterCustomerCommand;
import de.strasser.peter.hexagonal.application.customer.port.out.SaveCustomerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
class RegisterCustomerService implements RegisterCustomerUseCase {
    private final SaveCustomerAdapter saveUser;
    private final PasswordEncoder pwEncoder;

    @Override
    public void register(@Valid RegisterCustomerCommand registerCmd) {
        var encryptedPw = pwEncoder.encode(registerCmd.getClearPassword());
        var newCustomer = Customer.newCustomer(registerCmd.getName(), encryptedPw, registerCmd.getBirthDay());

        saveUser.upsert(newCustomer);
    }
}
