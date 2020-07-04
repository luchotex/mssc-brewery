package guru.springframework.msscbrewery.web.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-04 02:23
 */
@Service
public class CustomerServiceImpl implements CustomerService {
  @Override
  public CustomerDto getById(UUID customerId) {
    return CustomerDto.builder().id(UUID.randomUUID()).name("Luis Kupferberg").build();
  }
}
