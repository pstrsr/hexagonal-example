package de.strasser.peter.hexagonal.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterCustomerRequest {
  @Schema(description = "name of new customer", example = "Hans", required = true)
  String name;

  @Schema(description = "birthday of new customer", example = "1980-01-01", required = true)
  LocalDate birthDay;

  @Schema(description = "password of new customer", example = "S3cretPa$$word!", required = true)
  String password;
}
