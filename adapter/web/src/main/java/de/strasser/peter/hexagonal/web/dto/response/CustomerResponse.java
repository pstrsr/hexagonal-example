package de.strasser.peter.hexagonal.web.dto.response;

import de.strasser.peter.hexagonal.application.customer.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
  private BigInteger id;
  private String name;
  private LocalDate birthday;
  private Map<Address.AddressType, AddressResponse> addresses;
  private int age;
}
