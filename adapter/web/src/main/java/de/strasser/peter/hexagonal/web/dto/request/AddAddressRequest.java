package de.strasser.peter.hexagonal.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AddAddressRequest {
  @Schema(
      description = "Type of the address",
      example = "Electronics",
      allowableValues = {"BILLING", "SHIPPING", "DEFAULT"},
      required = true)
  String type;

  @Schema(description = "street", example = "Parkring 57", required = true)
  String street;

  @Schema(description = "city", example = "Garching b. München", required = true)
  String city;

  @Schema(description = "postal code", example = "85748", required = true)
  Integer zipCode;

  @Schema(description = "country", example = "Germany", required = true)
  String country;
}
