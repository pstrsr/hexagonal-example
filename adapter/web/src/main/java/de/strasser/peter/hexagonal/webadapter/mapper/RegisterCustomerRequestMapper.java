package de.strasser.peter.hexagonal.webadapter.mapper;

import de.strasser.peter.hexagonal.application.customer.port.in.commands.RegisterCustomerCommand;
import de.strasser.peter.hexagonal.webadapter.model.RegisterCustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RegisterCustomerRequestMapper {
    @Mapping(source = "password", target = "clearPassword")
    RegisterCustomerCommand toCmd(RegisterCustomerRequest registerCustomerRequest);
}
