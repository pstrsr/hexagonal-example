package de.strasser.peter.hexagonal.web.mapper;

import de.strasser.peter.hexagonal.application.port.in.commands.AddAddressCommand;
import de.strasser.peter.hexagonal.web.dto.request.AddAddressRequest;
import org.mapstruct.Mapper;

@Mapper
public interface AddAddressWebMapper {
  /**
   * Can not be mapped by MapStruct currently, due to bug in JDK 16.
   * https://github.com/mapstruct/mapstruct/issues/2294
   */
  default AddAddressCommand toCmd(AddAddressRequest addAddressRequest) {
    return new AddAddressCommand(
        addAddressRequest.getType(),
        addAddressRequest.getStreet(),
        addAddressRequest.getCity(),
        addAddressRequest.getZipCode(),
        addAddressRequest.getCountry());
  }
}
