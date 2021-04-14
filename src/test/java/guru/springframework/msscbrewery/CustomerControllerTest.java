package guru.springframework.msscbrewery;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.controller.CustomerController;
import guru.springframework.msscbrewery.web.model.CustomerDto;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	
	@MockBean
	private CustomerService customerService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	CustomerDto customerDto;
	
	@Before
	public void setUp() {
		customerDto = CustomerDto.builder().customerId(UUID.randomUUID())
				.name("Raphael")
				.build();
	}
	
	@Test
	public void getCustomer() throws Exception {
		given(customerService.getCustomerById(any(UUID.class))).willReturn(customerDto);
		
		mockMvc.perform(get("/api/v1/customer/" + customerDto.getCustomerId().toString())
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.customerId", is(customerDto.getCustomerId().toString())))
			.andExpect(jsonPath("$.name", is("Raphael")));
	}
}
