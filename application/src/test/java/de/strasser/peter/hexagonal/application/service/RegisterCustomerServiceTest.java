package de.strasser.peter.hexagonal.application.service;

import de.strasser.peter.hexagonal.application.port.in.commands.RegisterCustomerCommand;
import de.strasser.peter.hexagonal.application.port.out.AddressValidatorPort;
import de.strasser.peter.hexagonal.application.port.out.LoadCustomerPort;
import de.strasser.peter.hexagonal.application.port.out.SaveCustomerPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;

@SpringBootTest
class RegisterCustomerServiceTest {

  @Autowired private RegisterCustomerService sut;

  @MockBean private SaveCustomerPort saveCustomerAdapterMock;
  @MockBean private AddressValidatorPort addressValidatorAdapterMock;
  @MockBean private LoadCustomerPort loadCustomerAdapterMock;

  @BeforeEach
  public void setUp() {
    willDoNothing().given(saveCustomerAdapterMock).upsert(any());
  }

  @Test
  public void should_ThrowInvalidBirthDate_When_PassingDateInFuture() {
    RegisterCustomerCommand registerCustomerCommand =
        new RegisterCustomerCommand("Hans", LocalDate.now().plusDays(1), "secretPassword");

    assertThrows(
        ConstraintViolationException.class,
        () -> {
          sut.register(registerCustomerCommand);
        });
  }

  @Test
  public void should_ThrowInvalidPassword_When_PassingInsecurePassword() {
    LocalDate birthday = LocalDate.of(1980, 1, 1);
    RegisterCustomerCommand registerCustomerCommand =
        new RegisterCustomerCommand("Hans", birthday, "secretPassword");

    assertThrows(
        ConstraintViolationException.class,
        () -> {
          sut.register(registerCustomerCommand);
        });
  }

  @Test
  public void should_SaveUser() {
    LocalDate birthday = LocalDate.of(1980, 1, 1);
    RegisterCustomerCommand registerCustomerCommand =
        new RegisterCustomerCommand("Hans", birthday, "Ha012!321+dw");
    sut.register(registerCustomerCommand);
  }
}
