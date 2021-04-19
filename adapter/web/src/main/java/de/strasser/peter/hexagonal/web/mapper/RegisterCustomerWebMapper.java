package de.strasser.peter.hexagonal.web.mapper;

import de.strasser.peter.hexagonal.application.port.in.commands.RegisterCustomerCommand;
import de.strasser.peter.hexagonal.web.dto.request.RegisterCustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RegisterCustomerWebMapper {
  @Mapping(source = "password", target = "clearPassword")
  RegisterCustomerCommand toCmd(RegisterCustomerRequest registerCustomerRequest);
}
