package de.strasser.peter.hexagonal.web;

import de.strasser.peter.hexagonal.application.port.in.RegisterCustomerUseCase;
import de.strasser.peter.hexagonal.application.port.in.commands.RegisterCustomerCommand;
import de.strasser.peter.hexagonal.common.ReadStringResources;
import de.strasser.peter.hexagonal.web.mapper.RegisterCustomerWebMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(controllers = RegisterCustomerController.class)
@Import(RegisterCustomerWebMapperImpl.class)
class RegisterCustomerControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private RegisterCustomerUseCase registerCustomerUseCase;

  @Test
  public void should_RegisterUser_When_SendingValidRequest() throws Exception {
    final String body = ReadStringResources.readStringFromResource("valid_register_customer.json");
    mockMvc
        .perform(post("/v1/register").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isOk())
        .andReturn();

    var registerCmd = new RegisterCustomerCommand("meier", LocalDate.of(2010, 1, 1), "Ha11OMaMa!");

    then(registerCustomerUseCase).should().register(eq(registerCmd));
  }

  @Test
  public void should_DenyRequest_When_SendingInvalidDateFormat() throws Exception {
    final String body =
        ReadStringResources.readStringFromResource("invalid_date_register_customer.json");

    mockMvc
        .perform(post("/v1/register").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().is4xxClientError())
        .andReturn();
  }
}
