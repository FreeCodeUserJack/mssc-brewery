package guru.springframework.msscbrewery.services;

import java.util.UUID;

import guru.springframework.msscbrewery.web.model.CustomerDto;

public interface CustomerService {
	
	CustomerDto getCustomerById(UUID customerId);

	CustomerDto addNewCustomer(CustomerDto customerDto);

	void updateCustomer(UUID customerId, CustomerDto customerDto);

	void deleteCustomer(UUID customerId);
}
