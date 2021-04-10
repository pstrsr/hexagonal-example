package de.strasser.peter.hexagonal.webadapter.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AddAddressRequest {
    String type;
    String street;
    Integer houseNumber;
    Integer zipCode;
    String country;
}
