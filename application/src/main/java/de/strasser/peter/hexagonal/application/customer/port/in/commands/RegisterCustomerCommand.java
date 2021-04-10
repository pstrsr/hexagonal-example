package de.strasser.peter.hexagonal.application.customer.port.in.commands;


import de.strasser.peter.hexagonal.common.validators.SecurePassword;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterCustomerCommand {
    @NotEmpty String name;
    @Past LocalDate birthDay;
    @SecurePassword String clearPassword;
}
