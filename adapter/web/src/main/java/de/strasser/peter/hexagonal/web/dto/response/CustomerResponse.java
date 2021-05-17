package de.strasser.peter.hexagonal.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
  @Schema(description = "id of customer", example = "1", required = true)
  private BigInteger id;

  @Schema(description = "name of customer", example = "hans", required = true)
  private String name;

  @Schema(description = "birthday of customer", example = "1980-01-01", required = true)
  private LocalDate birthday;

  @Schema(description = "addresses of customer", required = true)
  private Map<AddressTypeResponse, AddressResponse> addresses;

  @Schema(description = "age of customer", example = "41", required = true)
  private int age;
}
