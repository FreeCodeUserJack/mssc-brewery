package guru.springframework.msscbrewery.web.controller.v2;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;

@RestController
@RequestMapping("/api/v2/beer")
public class BeerControllerV2 {
	private final BeerServiceV2 beerService;

    public BeerControllerV2(BeerServiceV2 beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    
    @PostMapping // create a new beer
    public ResponseEntity<HttpHeaders> handlePost(@RequestBody BeerDtoV2 beerDto) {
    	
    	BeerDtoV2 savedDto = beerService.saveNewBeer(beerDto);
    	
    	// TODO add hostname later
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Location", "/api/v2/beer" + savedDto.getId().toString());
    	
    	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping({"/{beerId}"})
    public ResponseEntity<HttpStatus> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDtoV2 beerDto) {
    	beerService.updateBeer(beerId, beerDto);
    	
    	return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT); // 204
    }
    
    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable("beerId") UUID beerId) {
    	beerService.deleteById(beerId);
    }

}
