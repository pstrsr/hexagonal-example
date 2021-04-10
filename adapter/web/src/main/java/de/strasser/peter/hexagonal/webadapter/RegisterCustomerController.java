package de.strasser.peter.hexagonal.webadapter;

import de.strasser.peter.hexagonal.application.customer.port.in.RegisterCustomerUseCase;
import de.strasser.peter.hexagonal.webadapter.mapper.RegisterCustomerRequestMapper;
import de.strasser.peter.hexagonal.webadapter.model.RegisterCustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegisterCustomerController {
    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final RegisterCustomerRequestMapper registerCustomerMapper;

    @PostMapping("/v1/register")
    public void registerCustomer(@RequestBody RegisterCustomerRequest registerCustomerRequest) {
        registerCustomerUseCase.register(registerCustomerMapper.toCmd(registerCustomerRequest));
    }
}
