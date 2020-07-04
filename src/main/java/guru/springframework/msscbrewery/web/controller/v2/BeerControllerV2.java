package guru.springframework.msscbrewery.web.controller.v2;

import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import guru.springframework.msscbrewery.web.services.v2.BeerServiceV2;
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
 * @created 2020-07-04 18:06
 */
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

  private BeerServiceV2 beerServiceV2;

  public BeerControllerV2(BeerServiceV2 beerServiceV2) {
    this.beerServiceV2 = beerServiceV2;
  }

  @GetMapping({"/{beerId}"})
  public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId) {

    return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity create(@RequestBody BeerDtoV2 beerDtoV2) {
    BeerDtoV2 createdBeer = beerServiceV2.create(beerDtoV2);
    HttpHeaders httpHeaders = new HttpHeaders();
    // todo add the hostname url
    httpHeaders.add("Location", "/api/v2/beer/" + createdBeer.getId().toString());

    return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
  }

  @PutMapping({"/{beerId}"})
  public ResponseEntity update(
      @PathVariable("beerId") UUID beerId, @RequestBody BeerDtoV2 beerDtoV2) {
    beerServiceV2.update(beerId, beerDtoV2);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping({"{beerId}"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable("beerId") UUID beerId) {
    beerServiceV2.deleteById(beerId);
  }
}