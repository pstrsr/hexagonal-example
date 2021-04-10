package de.strasser.peter.hexagonal.persistence.mapper;

import de.strasser.peter.hexagonal.application.customer.domain.Address;
import de.strasser.peter.hexagonal.application.customer.domain.Customer;
import de.strasser.peter.hexagonal.persistence.model.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.HashMap;

@Mapper
public class CustomerMapper {
    Customer toDomain(CustomerEntity customerEntity) {
        final HashMap<Address.AddressType, Address> addresses = createAddressMap(customerEntity);
        return Customer.createCustomer(
                customerEntity.getId(),
                customerEntity.getName(),
                customerEntity.getHashedPassword(),
                customerEntity.getBirthday(),
                addresses,
                customerEntity.isActive());
    }


    CustomerEntity toDbEntity(Customer customer) {
        return null;
    }

    private HashMap<Address.AddressType, Address> createAddressMap(CustomerEntity customerEntity) {
        final HashMap<Address.AddressType, Address> addresses = new HashMap<>();
        if (customerEntity.getStreet() != null) {
            addresses.put(Address.AddressType.DEFAULT,
                    new Address(
                            customerEntity.getStreet(),
                            customerEntity.getHouseNumber(),
                            customerEntity.getZipCode(),
                            customerEntity.getCountry()));
        }
        if (customerEntity.getBillingStreet() != null) {
            addresses.put(Address.AddressType.SHIPPING,
                    new Address(
                            customerEntity.getShippingStreet(),
                            customerEntity.getShippingHouseNumber(),
                            customerEntity.getShippingZipCode(),
                            customerEntity.getShippingCountry()));
        }
        if (customerEntity.getBillingStreet() != null) {
            addresses.put(Address.AddressType.BILLING,
                    new Address(
                            customerEntity.getBillingStreet(),
                            customerEntity.getBillingHouseNumber(),
                            customerEntity.getBillingZipCode(),
                            customerEntity.getBillingCountry()));
        }
        return addresses;
    }
}
