package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;

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

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    
    @PostMapping // create a new beer
    public ResponseEntity<HttpHeaders> handlePost(@RequestBody BeerDto beerDto) {
    	
    	BeerDto savedDto = beerService.saveNewBeer(beerDto);
    	
    	// TODO add hostname later
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Location", "/api/v1/beer" + savedDto.getId().toString());
    	
    	return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    @PutMapping({"/{beerId}"})
    public ResponseEntity<HttpStatus> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
    	beerService.updateBeer(beerId, beerDto);
    	
    	return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT); // 204
    }
    
    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable("beerId") UUID beerId) {
    	beerService.deleteById(beerId);
    }

}
