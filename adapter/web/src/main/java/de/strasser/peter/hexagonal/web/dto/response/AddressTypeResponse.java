package de.strasser.peter.hexagonal.web.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AddressTypeResponse {
  @JsonProperty("default")
  DEFAULT,
  @JsonProperty("shipping")
  SHIPPING,
  @JsonProperty("billing")
  BILLING;
}
