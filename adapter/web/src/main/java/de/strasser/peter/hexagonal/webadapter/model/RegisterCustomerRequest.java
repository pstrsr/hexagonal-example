package de.strasser.peter.hexagonal.webadapter.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterCustomerRequest {
    String name;
    LocalDate birthDay;
    String clearPassword;
}
