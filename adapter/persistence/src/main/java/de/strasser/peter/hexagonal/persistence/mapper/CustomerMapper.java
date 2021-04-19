package de.strasser.peter.hexagonal.persistence.mapper;

import de.strasser.peter.hexagonal.application.domain.Address;
import de.strasser.peter.hexagonal.application.domain.Customer;
import de.strasser.peter.hexagonal.persistence.model.CustomerEntity;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerMapper {
  List<Customer> toDomain(List<CustomerEntity> customerEntityList);

  default Customer toDomain(CustomerEntity customerEntity) {
    final HashMap<Address.AddressType, Address> addresses = createAddressMap(customerEntity);
    return Customer.createCustomer(
        customerEntity.getId(),
        customerEntity.getName(),
        customerEntity.getHashedPassword(),
        customerEntity.getBirthday(),
        addresses,
        customerEntity.isActive());
  }

  default CustomerEntity toDbEntity(Customer customer) {
    return new CustomerEntity(
        customer.getId(),
        customer.getName(),
        customer.getHashedPassword(),
        customer.getBirthday(),
        customer.isActive(),
        getAddressAttribute(customer, Address.AddressType.DEFAULT, Address::getStreet),
        getAddressAttribute(customer, Address.AddressType.DEFAULT, Address::getHouseNumber),
        getAddressAttribute(customer, Address.AddressType.DEFAULT, Address::getZipCode),
        getAddressAttribute(customer, Address.AddressType.DEFAULT, Address::getCountry),
        getAddressAttribute(customer, Address.AddressType.SHIPPING, Address::getStreet),
        getAddressAttribute(customer, Address.AddressType.SHIPPING, Address::getHouseNumber),
        getAddressAttribute(customer, Address.AddressType.SHIPPING, Address::getZipCode),
        getAddressAttribute(customer, Address.AddressType.SHIPPING, Address::getCountry),
        getAddressAttribute(customer, Address.AddressType.BILLING, Address::getStreet),
        getAddressAttribute(customer, Address.AddressType.BILLING, Address::getHouseNumber),
        getAddressAttribute(customer, Address.AddressType.BILLING, Address::getZipCode),
        getAddressAttribute(customer, Address.AddressType.BILLING, Address::getCountry),
        null);
  }

  private <T> T getAddressAttribute(
      Customer customer, Address.AddressType type, Converter<Address, T> getter) {
    final Map<Address.AddressType, Address> addresses = customer.getAddresses();
    if (addresses != null) {
      final Address address = addresses.get(type);
      if (address != null) {
        return getter.convert(address);
      }
    }
    return null;
  }

  private HashMap<Address.AddressType, Address> createAddressMap(CustomerEntity customerEntity) {
    final HashMap<Address.AddressType, Address> addresses = new HashMap<>();
    if (customerEntity.getStreet() != null) {
      addresses.put(
          Address.AddressType.DEFAULT,
          new Address(
              customerEntity.getStreet(),
              customerEntity.getHouseNumber(),
              customerEntity.getZipCode(),
              customerEntity.getCountry()));
    }
    if (customerEntity.getBillingStreet() != null) {
      addresses.put(
          Address.AddressType.SHIPPING,
          new Address(
              customerEntity.getShippingStreet(),
              customerEntity.getShippingHouseNumber(),
              customerEntity.getShippingZipCode(),
              customerEntity.getShippingCountry()));
    }
    if (customerEntity.getBillingStreet() != null) {
      addresses.put(
          Address.AddressType.BILLING,
          new Address(
              customerEntity.getBillingStreet(),
              customerEntity.getBillingHouseNumber(),
              customerEntity.getBillingZipCode(),
              customerEntity.getBillingCountry()));
    }
    return addresses;
  }
}
