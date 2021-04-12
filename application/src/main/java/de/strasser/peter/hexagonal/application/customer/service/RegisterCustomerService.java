package de.strasser.peter.hexagonal.application.customer.service;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;
import de.strasser.peter.hexagonal.application.customer.port.in.RegisterCustomerUseCase;
import de.strasser.peter.hexagonal.application.customer.port.in.commands.RegisterCustomerCommand;
import de.strasser.peter.hexagonal.application.customer.port.out.SaveCustomerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
class RegisterCustomerService implements RegisterCustomerUseCase {
    private final SaveCustomerPort saveUser;

    @Override
    public void register(@Valid RegisterCustomerCommand registerCmd) {
        var encryptedPw = this.superSecureHashingAlgorithm(registerCmd.getClearPassword());
        var newCustomer = Customer.newCustomer(registerCmd.getName(), encryptedPw, registerCmd.getBirthDay());

        saveUser.upsert(newCustomer);
    }

    private String superSecureHashingAlgorithm(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
