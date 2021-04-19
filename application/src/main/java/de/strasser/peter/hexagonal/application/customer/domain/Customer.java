package de.strasser.peter.hexagonal.application.customer.domain;

import de.strasser.peter.hexagonal.application.customer.exception.TooOldToDeactivateExc;
import de.strasser.peter.hexagonal.application.customer.exception.TooYoungExc;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
@ToString
public class Customer {
  private final BigInteger id;
  private final String name;
  private final String hashedPassword;
  private final LocalDate birthday;
  private final int age;
  private final Map<Address.AddressType, Address> addresses;
  private boolean active;

  private Customer(
      BigInteger id,
      String name,
      String hashedPassword,
      LocalDate birthDate,
      Map<Address.AddressType, Address> addresses,
      boolean active) {
    this.id = id;
    this.active = active;
    this.age = Period.between(birthDate, LocalDate.now()).getYears();
    this.birthday = birthDate;
    this.name = name;
    this.hashedPassword = hashedPassword;
    this.addresses = addresses != null ? addresses : new HashMap<>();
    if (this.age < 18) {
      throw new TooYoungExc(age);
    }
  }

  public static Customer newCustomer(String name, String hashedPassword, LocalDate birthDate) {
    return new Customer(null, name, hashedPassword, birthDate, null, false);
  }

  public static Customer createCustomer(
      BigInteger id,
      String name,
      String hashedPassword,
      LocalDate birthDate,
      Map<Address.AddressType, Address> addresses,
      boolean active) {
    return new Customer(id, name, hashedPassword, birthDate, addresses, active);
  }

  public void addAddresses(Map<Address.AddressType, Address> addresses) {
    this.addresses.putAll(addresses);

    if (this.addresses.containsKey(Address.AddressType.DEFAULT)) {
      this.active = true;
    }
  }

  public void deactivate() {
    if (this.age < 50) {
      this.active = false;
    } else {
      throw new TooOldToDeactivateExc(this.age);
    }
  }
}
