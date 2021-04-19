package de.strasser.peter.hexagonal.application.port.in.commands;

import de.strasser.peter.hexagonal.application.validator.SecurePassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterCustomerCommand {
  @NotEmpty String name;
  @Past LocalDate birthDay;
  @SecurePassword String clearPassword;
}
