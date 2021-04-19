package de.strasser.peter.hexagonal.web;

import de.strasser.peter.hexagonal.application.customer.port.in.QueryAllCustomersCRUD;
import de.strasser.peter.hexagonal.common.Adapter;
import de.strasser.peter.hexagonal.web.dto.response.CustomerResponse;
import de.strasser.peter.hexagonal.web.mapper.CustomerWebMapper;
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

  @GetMapping("/v1/customers")
  public List<CustomerResponse> getAllCustomers() {
    return customerMapper.toResponse(this.queryAllCustomersCRUD.getAll());
  }
}
