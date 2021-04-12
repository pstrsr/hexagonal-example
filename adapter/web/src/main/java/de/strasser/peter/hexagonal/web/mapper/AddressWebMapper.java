package de.strasser.peter.hexagonal.web.mapper;

import de.strasser.peter.hexagonal.application.customer.domain.Address;
import de.strasser.peter.hexagonal.web.dto.response.AddressResponse;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface AddressWebMapper {
  AddressResponse toResponse(Address address);

  Map<Address.AddressType, AddressResponse> toResponse(Map<Address.AddressType, Address> addresses);
}
