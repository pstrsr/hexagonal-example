package de.strasser.peter.hexagonal.web.mapper;

import de.strasser.peter.hexagonal.application.customer.port.in.commands.AddAddressCommand;
import de.strasser.peter.hexagonal.web.dto.request.AddAddressRequest;
import org.mapstruct.Mapper;

@Mapper
public interface AddAddressWebMapper {
  AddAddressCommand toCmd(AddAddressRequest addAddressRequest);
}
