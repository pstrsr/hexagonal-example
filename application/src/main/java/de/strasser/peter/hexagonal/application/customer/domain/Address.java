package de.strasser.peter.hexagonal.application.customer.domain;

import de.strasser.peter.hexagonal.application.customer.exception.AddressTypeDoesNotExistsExc;
import lombok.Value;

@Value
public class Address {
    String street;
    int houseNumber;
    int zipCode;
    String country;


    public enum AddressType {
        DEFAULT,
        SHIPPING,
        BILLING;

        public static AddressType fromString(String type) {
            return switch (type.toUpperCase()) {
                case "DEFAULT" -> DEFAULT;
                case "SHIPPING" -> SHIPPING;
                case "BILLING" -> BILLING;
                default -> throw new AddressTypeDoesNotExistsExc(type);
            };
        }
    }
}
