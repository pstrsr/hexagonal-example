package de.strasser.peter.hexagonal.web;

import de.strasser.peter.hexagonal.application.port.in.RegisterCustomerUseCase;
import de.strasser.peter.hexagonal.common.Adapter;
import de.strasser.peter.hexagonal.web.dto.request.RegisterCustomerRequest;
import de.strasser.peter.hexagonal.web.mapper.RegisterCustomerWebMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Adapter
@RestController
@AllArgsConstructor
public class RegisterCustomerController {
  private final RegisterCustomerUseCase registerCustomerUseCase;
  private final RegisterCustomerWebMapper registerCustomerMapper;

  @PostMapping("/v1/register")
  public void registerCustomer(@RequestBody RegisterCustomerRequest registerCustomerRequest) {
    registerCustomerUseCase.register(registerCustomerMapper.toCmd(registerCustomerRequest));
  }
}
