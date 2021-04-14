package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
    }

	@Override
	public BeerDto saveNewBeer(@RequestBody BeerDto beerDto) {
		// persistence layer here
		
		System.out.println(beerDto.toString());
		
		return BeerDto.builder().id(UUID.randomUUID()).build();
		
		
	}

	@Override
	public void updateBeer(UUID beerId, BeerDto beerDto) {
		// TODO would add real impl to update beer
		
	}

	@Override
	public void deleteById(UUID beerId) {
		// TODO Auto-generated method stub
		log.debug(beerId.toString());
	}
}
