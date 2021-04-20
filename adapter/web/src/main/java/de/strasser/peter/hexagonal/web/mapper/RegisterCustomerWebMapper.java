package de.strasser.peter.hexagonal.web.mapper;

import de.strasser.peter.hexagonal.application.port.in.commands.RegisterCustomerCommand;
import de.strasser.peter.hexagonal.web.dto.request.RegisterCustomerRequest;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterCustomerWebMapper {
  /**
   * Can not be mapped by MapStruct currently, due to bug in JDK 16.
   * https://github.com/mapstruct/mapstruct/issues/2294 @Mapping(source = "password", target =
   * "clearPassword")
   */
  default RegisterCustomerCommand toCmd(RegisterCustomerRequest registerCustomerRequest) {
    return new RegisterCustomerCommand(
        registerCustomerRequest.getName(),
        registerCustomerRequest.getBirthDay(),
        registerCustomerRequest.getPassword());
  }
}
