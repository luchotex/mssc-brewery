package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.services.BeerService;
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
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-04 01:12
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

  private BeerService beerService;

  public BeerController(BeerService beerService) {
    this.beerService = beerService;
  }

  @GetMapping({"/{beerId}"})
  public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {

    return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity create(@RequestBody BeerDto beerDto) {
    BeerDto createdBeer = beerService.create(beerDto);
    HttpHeaders httpHeaders = new HttpHeaders();
    // todo add the hostname url
    httpHeaders.add("Location", "/api/v1/beer/" + createdBeer.getId().toString());

    return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
  }

  @PutMapping({"/{beerId}"})
  public ResponseEntity update(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
    beerService.update(beerId, beerDto);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping({"{beerId}"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable("beerId") UUID beerId) {
    beerService.deleteById(beerId);
  }
}
