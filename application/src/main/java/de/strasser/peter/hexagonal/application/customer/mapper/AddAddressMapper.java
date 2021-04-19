package de.strasser.peter.hexagonal.application.customer.mapper;

import de.strasser.peter.hexagonal.application.customer.port.in.commands.AddAddressCommand;
import de.strasser.peter.hexagonal.application.customer.port.out.commands.ValidateAddressCommand;
import org.mapstruct.Mapper;

@Mapper
public interface AddAddressMapper {
  ValidateAddressCommand toOutCmd(AddAddressCommand addAddressCommand);
}
