package de.strasser.peter.hexagonal.web;

import de.strasser.peter.hexagonal.application.customer.port.in.AddAddressUseCase;
import de.strasser.peter.hexagonal.application.customer.port.in.commands.AddAddressCommand;
import de.strasser.peter.hexagonal.common.validators.TestUtils;
import de.strasser.peter.hexagonal.web.mapper.AddAddressWebMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(controllers = AddAddressController.class)
@Import(AddAddressWebMapperImpl.class)
class AddAddressControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private AddAddressUseCase addAddressUseCase;

  @Test
  public void should_AddAddress() throws Exception {
    final String body = TestUtils.readStringFromResource("valid_add_address.json");
    final int customerId = 1231231;

    mockMvc
        .perform(
            post("/v1/customer/address?customerId=" + customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
        .andExpect(status().isOk())
        .andReturn();

    var addAddressCmd = new AddAddressCommand("default", "street", 59, 85748, "Germany");

    then(addAddressUseCase)
        .should()
        .addAddresses(eq(BigInteger.valueOf(customerId)), eq(List.of(addAddressCmd)));
  }
}
