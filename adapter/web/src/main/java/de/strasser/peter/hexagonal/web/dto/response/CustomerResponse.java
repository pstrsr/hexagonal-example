package de.strasser.peter.hexagonal.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
  private BigInteger id;
  private String name;
  private String hashedPassword;
  private LocalDate birthday;
  private int age;
}
