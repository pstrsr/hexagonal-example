package de.strasser.peter.hexagonal.web;

import de.strasser.peter.hexagonal.application.port.in.RegisterCustomerUseCase;
import de.strasser.peter.hexagonal.common.Adapter;
import de.strasser.peter.hexagonal.web.dto.request.RegisterCustomerRequest;
import de.strasser.peter.hexagonal.web.errors.ErrorHandling;
import de.strasser.peter.hexagonal.web.mapper.RegisterCustomerWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @Operation(
      summary = "Register Customer",
      description = "Register a new customer",
      tags = {"Customer"})
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid Parameter")
      })
  @PostMapping("/v1/register")
  public void registerCustomer(@RequestBody RegisterCustomerRequest registerCustomerRequest) {
    registerCustomerUseCase.register(registerCustomerMapper.toCmd(registerCustomerRequest));
  }
}
