package de.strasser.peter.hexagonal.web;

import de.strasser.peter.hexagonal.application.customer.port.in.AddAddressUseCase;
import de.strasser.peter.hexagonal.application.customer.port.in.commands.AddAddressCommand;
import de.strasser.peter.hexagonal.web.mapper.AddAddressRequestMapper;
import de.strasser.peter.hexagonal.web.model.AddAddressRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddAddressController {
    private final AddAddressUseCase addAddressUseCase;
    private final AddAddressRequestMapper addAddressMapper;


    @PostMapping("/v1/address")
    public void addAddress(@RequestParam BigInteger customerId, @RequestBody AddAddressRequest addAddressRequest) {
        final List<AddAddressCommand> addAddressCmds = List.of(addAddressMapper.toCmd(addAddressRequest));
        addAddressUseCase.addAddresses(customerId, addAddressCmds);
    }
}
