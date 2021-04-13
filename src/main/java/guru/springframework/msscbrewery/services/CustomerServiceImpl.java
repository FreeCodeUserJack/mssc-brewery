package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		// TODO Auto-generated method stub
		return CustomerDto.builder().customerId(UUID.randomUUID())
			.name("Johnny").build();
	}

}
