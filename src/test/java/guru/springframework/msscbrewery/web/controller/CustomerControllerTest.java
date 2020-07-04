package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import guru.springframework.msscbrewery.web.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

  public static final String CUSTOMER_URL = "/api/v1/customer/";
  @MockBean private CustomerService customerService;

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  private CustomerDto validCustomer;

  @Before
  public void setUp() throws Exception {
    validCustomer = CustomerDto.builder().id(UUID.randomUUID()).name("Luis Kupferberg").build();
  }

  @Test
  public void getById_successfulTest() throws Exception {
    // given
    given(customerService.getById(any(UUID.class))).willReturn(validCustomer);

    // when and then
    mockMvc
        .perform(get(CUSTOMER_URL + validCustomer.getId()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
        .andExpect(jsonPath("$.name", is(validCustomer.getName())));
  }

  @Test
  public void create_successfulTest() throws Exception {
    // given
    CustomerDto customerDto = validCustomer;
    customerDto.setId(null);
    String customerJson = objectMapper.writeValueAsString(validCustomer);
    CustomerDto createdCustomer =
        CustomerDto.builder().id(UUID.randomUUID()).name("new Customer").build();

    given(customerService.create(any(CustomerDto.class))).willReturn(createdCustomer);

    // when and then
    mockMvc
        .perform(post(CUSTOMER_URL).contentType(MediaType.APPLICATION_JSON).content(customerJson))
        .andExpect(status().isCreated());
  }

  @Test
  public void update_successfulTest() throws Exception {
    // given
    CustomerDto customerDto = validCustomer;
    String customerJson = objectMapper.writeValueAsString(validCustomer);

    mockMvc
        .perform(
            put(CUSTOMER_URL + validCustomer.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
        .andExpect(status().isNoContent());

    then(customerService).should().update(any(), any());
  }

  @Test
  public void deleteById_successfulTest() throws Exception {
    // given
    UUID id = UUID.randomUUID();

    // when and then
    mockMvc.perform(delete(CUSTOMER_URL + id.toString())).andExpect(status().isNoContent());
  }
}
