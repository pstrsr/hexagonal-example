package de.strasser.peter.hexagonal.application.service;

import de.strasser.peter.hexagonal.application.domain.Customer;
import de.strasser.peter.hexagonal.application.port.in.RegisterCustomerUseCase;
import de.strasser.peter.hexagonal.application.port.in.commands.RegisterCustomerCommand;
import de.strasser.peter.hexagonal.application.port.out.SaveCustomerPort;
import de.strasser.peter.hexagonal.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;

@UseCase
@Validated
@Transactional
@RequiredArgsConstructor
class RegisterCustomerService implements RegisterCustomerUseCase {
  private final SaveCustomerPort saveUser;

  @Override
  public void register(@Valid RegisterCustomerCommand registerCmd) {
    var encryptedPw = this.superSecureHashingAlgorithm(registerCmd.clearPassword());
    var newCustomer =
        Customer.newCustomer(registerCmd.name(), encryptedPw, registerCmd.birthDay());

    saveUser.upsert(newCustomer);
  }

  private String superSecureHashingAlgorithm(String s) {
    return new StringBuilder(s).reverse().toString();
  }
}
