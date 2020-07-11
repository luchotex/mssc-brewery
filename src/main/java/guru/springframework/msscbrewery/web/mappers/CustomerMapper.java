package guru.springframework.msscbrewery.web.mappers;

import guru.springframework.msscbrewery.domain.Customer;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-11 19:19
 */
@Mapper
public interface CustomerMapper {

  CustomerDto customerToCustomerDto(Customer customer);

  Customer customerDtoToCustomer(CustomerDto customerDto);
}
