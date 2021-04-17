package de.strasser.peter.hexagonal.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CustomerEntity {
  @Id private BigInteger id;
  private String name;
  private String hashedPassword;
  private LocalDate birthday;
  private boolean active;

  private String street;
  private Integer houseNumber;
  private Integer zipCode;
  private String country;

  private String shippingStreet;
  private Integer shippingHouseNumber;
  private Integer shippingZipCode;
  private String shippingCountry;

  private String billingStreet;
  private Integer billingHouseNumber;
  private Integer billingZipCode;
  private String billingCountry;

  @CreatedDate private LocalDateTime createdAt;
}
