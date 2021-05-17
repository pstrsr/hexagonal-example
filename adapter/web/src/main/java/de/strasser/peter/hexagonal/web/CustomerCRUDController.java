package de.strasser.peter.hexagonal.web;

import de.strasser.peter.hexagonal.application.port.in.QueryAllCustomersCRUD;
import de.strasser.peter.hexagonal.common.Adapter;
import de.strasser.peter.hexagonal.web.dto.response.CustomerResponse;
import de.strasser.peter.hexagonal.web.mapper.CustomerWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Adapter
@RestController
@RequiredArgsConstructor
public class CustomerCRUDController {
  private final QueryAllCustomersCRUD queryAllCustomersCRUD;
  private final CustomerWebMapper customerMapper;

  @Operation(
      summary = "Get all customers",
      description = "Returns all customers",
      tags = {"Customer"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "successful operation",
            content =
                @Content(
                    array =
                        @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))))
      })
  @GetMapping("/v1/customers")
  public List<CustomerResponse> getAllCustomers() {
    return customerMapper.toResponse(this.queryAllCustomersCRUD.getAll());
  }
}
