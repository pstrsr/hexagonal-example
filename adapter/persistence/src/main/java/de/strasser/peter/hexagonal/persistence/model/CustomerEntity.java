package de.strasser.peter.hexagonal.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    private Integer id;
    private String name;
    private String hashedPassword;
    private LocalDate birthday;
    private boolean active;

    private String street;
    private int houseNumber;
    private int zipCode;
    private String country;

    private String shippingStreet;
    private int shippingHouseNumber;
    private int shippingZipCode;
    private String shippingCountry;

    private String billingStreet;
    private int billingHouseNumber;
    private int billingZipCode;
    private String billingCountry;

}
