package de.strasser.peter.hexagonal.web.mapper;

import de.strasser.peter.hexagonal.application.customer.domain.Customer;
import de.strasser.peter.hexagonal.web.dto.response.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerWebMapper {
  CustomerResponse toResponse(Customer customer);

  List<CustomerResponse> toResponse(List<Customer> customer);
}
