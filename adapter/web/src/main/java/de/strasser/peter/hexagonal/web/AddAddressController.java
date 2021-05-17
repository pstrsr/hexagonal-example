package de.strasser.peter.hexagonal.web;

import de.strasser.peter.hexagonal.application.port.in.AddAddressUseCase;
import de.strasser.peter.hexagonal.application.port.in.commands.AddAddressCommand;
import de.strasser.peter.hexagonal.common.Adapter;
import de.strasser.peter.hexagonal.web.dto.request.AddAddressRequest;
import de.strasser.peter.hexagonal.web.errors.ErrorHandling;
import de.strasser.peter.hexagonal.web.mapper.AddAddressWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Adapter
@RestController
@RequiredArgsConstructor
public class AddAddressController {
  private final AddAddressUseCase addAddressUseCase;
  private final AddAddressWebMapper addAddressMapper;

  @Operation(
      summary = "Add Address",
      description = "Add an address to a customer by id.",
      tags = {"Address"})
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "successful operation"),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid Parameter",
            content =
                @Content(schema = @Schema(implementation = ErrorHandling.ErrorResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Customer does not exist",
            content =
                @Content(schema = @Schema(implementation = ErrorHandling.ErrorResponse.class)))
      })
  @PostMapping("/v1/customer/address")
  public void addAddress(
      @RequestParam BigInteger customerId, @RequestBody AddAddressRequest addAddressRequest) {
    final List<AddAddressCommand> addAddressCmds =
        List.of(addAddressMapper.toCmd(addAddressRequest));
    addAddressUseCase.addAddresses(customerId, addAddressCmds);
  }
}
