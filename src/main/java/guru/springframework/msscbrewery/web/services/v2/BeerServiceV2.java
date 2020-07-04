package guru.springframework.msscbrewery.web.services.v2;

import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-04 18:06
 */
public interface BeerServiceV2 {
    BeerDtoV2 getBeerById(UUID beerId);

    BeerDtoV2 create(BeerDtoV2 beerDtoV2);

    void update(UUID beerId, BeerDtoV2 beerDtoV2);

    void deleteById(UUID beerId);
}
