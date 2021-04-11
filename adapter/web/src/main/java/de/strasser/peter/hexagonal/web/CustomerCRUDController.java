package de.strasser.peter.hexagonal.web;

import de.strasser.peter.hexagonal.application.customer.port.in.QueryAllCustomersCRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerCRUDController {
  private final QueryAllCustomersCRUD queryAllCustomersCRUD;

  @GetMapping("/v1/customers")
  public Object getAllCustomers() {
    return this.queryAllCustomersCRUD.getAll();
  }
}
