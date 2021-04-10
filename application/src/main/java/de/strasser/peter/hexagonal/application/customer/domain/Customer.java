package de.strasser.peter.hexagonal.application.customer.domain;

import de.strasser.peter.hexagonal.application.customer.exception.DefaultAdressRequiredToActivateExc;
import de.strasser.peter.hexagonal.application.customer.exception.UserIsTooYoungExc;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Customer {
    private final Integer id;
    private String name;
    private String hashedPassword;
    private LocalDate birthday;
    private int age;
    private Map<Address.AddressType, Address> addresses;
    private boolean active;

    private Customer(Integer id, String name, String hashedPassword, LocalDate birthDate, Map<Address.AddressType, Address> addresses, boolean active) {
        this.id = id;
        this.active = active;
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
        this.birthday = birthDate;
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.addresses = addresses == null ? addresses : new HashMap<>();
        if (this.age < 18) {
            throw new UserIsTooYoungExc(age);
        }
    }

    public static Customer newCustomer(
            String name,
            String hashedPassword,
            LocalDate birthDate) {
        return new Customer(
                null,
                name,
                hashedPassword,
                birthDate,
                null,
                true);
    }

    public static Customer createCustomer(
            Integer id,
            String name,
            String hashedPassword,
            LocalDate birthDate,
            Map<Address.AddressType, Address> addresses,
            boolean active) {
        return new Customer(
                id, name, hashedPassword,
                birthDate,
                addresses,
                active);
    }

    public void activateCustomer() {
        if (this.addresses == null || this.addresses.containsKey(Address.AddressType.DEFAULT)) {
            throw new DefaultAdressRequiredToActivateExc();
        }
        this.active = true;
    }

    public void addAddresses(Map<Address.AddressType, Address> addresses) {
        this.addresses.putAll(addresses);

        if (this.addresses.containsKey(Address.AddressType.DEFAULT)) {
            this.activateCustomer();
        }
    }
}
