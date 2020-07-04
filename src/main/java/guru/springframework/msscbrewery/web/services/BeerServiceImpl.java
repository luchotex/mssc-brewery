package guru.springframework.msscbrewery.web.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-04 01:26
 */
@Service
@Slf4j
public class BeerServiceImpl implements BeerService {
  @Override
  public BeerDto getBeerById(UUID beerId) {
    return BeerDto.builder()
        .id(UUID.randomUUID())
        .beerName("Galaxy Cat")
        .beerStyle("Pale Ale")
        .build();
  }

  @Override
  public BeerDto create(BeerDto beerDto) {
    return BeerDto.builder().id(UUID.randomUUID()).build();
  }

  @Override
  public void update(UUID beerId, BeerDto beerDto) {
    // TODO implement the logic
  }

  @Override
  public void deleteById(UUID beerId) {
    log.debug("Deleting beer by Id...");
  }
}
