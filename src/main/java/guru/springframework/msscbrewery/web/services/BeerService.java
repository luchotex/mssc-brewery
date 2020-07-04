package guru.springframework.msscbrewery.web.services;

import guru.springframework.msscbrewery.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-04 01:25
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto create(BeerDto beerDto);
}
