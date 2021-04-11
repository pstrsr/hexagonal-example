package de.strasser.peter.hexagonal.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AddAddressRequest {
    String type;
    String street;
    Integer houseNumber;
    Integer zipCode;
    String country;
}
