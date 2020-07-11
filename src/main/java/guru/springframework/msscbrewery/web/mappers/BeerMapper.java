package guru.springframework.msscbrewery.web.mappers;

import guru.springframework.msscbrewery.domain.Beer;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-11 19:03
 */
@Mapper
public interface BeerMapper {

  BeerDto beerToBeerDtp(Beer beer);

  Beer beerDtoToBeer(BeerDto beerDto);
}
