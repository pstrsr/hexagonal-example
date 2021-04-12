package de.strasser.peter.hexagonal.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
  private String street;
  private Integer houseNumber;
  private Integer zipCode;
  private String country;
}
