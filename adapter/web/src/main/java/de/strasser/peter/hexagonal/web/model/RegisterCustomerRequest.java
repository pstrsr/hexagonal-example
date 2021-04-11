package de.strasser.peter.hexagonal.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterCustomerRequest {
    String name;
    LocalDate birthDay;
    String password;
}
