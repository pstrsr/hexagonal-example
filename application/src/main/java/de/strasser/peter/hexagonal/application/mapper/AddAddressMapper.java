package de.strasser.peter.hexagonal.application.mapper;

import de.strasser.peter.hexagonal.application.port.in.commands.AddAddressCommand;
import de.strasser.peter.hexagonal.application.port.out.commands.ValidateAddressCommand;
import org.mapstruct.Mapper;

@Mapper
public interface AddAddressMapper {
  ValidateAddressCommand toOutCmd(AddAddressCommand addAddressCommand);
}
