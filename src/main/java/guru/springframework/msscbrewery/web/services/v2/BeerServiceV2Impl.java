package guru.springframework.msscbrewery.web.services.v2;

import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-04 18:10
 */
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
  @Override
  public BeerDtoV2 getBeerById(UUID beerId) {
    return null;
  }

  @Override
  public BeerDtoV2 create(BeerDtoV2 beerDtoV2) {
    return null;
  }

  @Override
  public void update(UUID beerId, BeerDtoV2 beerDtoV2) {}

  @Override
  public void deleteById(UUID beerId) {}
}
