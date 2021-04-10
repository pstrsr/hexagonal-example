package de.strasser.peter.hexagonal.webadapter.mapper;

import de.strasser.peter.hexagonal.application.customer.port.in.commands.RegisterCustomerCommand;
import de.strasser.peter.hexagonal.webadapter.model.RegisterCustomerRequest;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterCustomerRequestMapper {
    RegisterCustomerCommand toCmd(RegisterCustomerRequest registerCustomerRequest);
}
