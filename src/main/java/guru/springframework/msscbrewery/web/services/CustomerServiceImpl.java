package guru.springframework.msscbrewery.web.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-04 02:23
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
  @Override
  public CustomerDto getById(UUID customerId) {
    return CustomerDto.builder().id(UUID.randomUUID()).name("Luis Kupferberg").build();
  }

  @Override
  public CustomerDto create(CustomerDto customerDto) {
    return CustomerDto.builder().id(UUID.randomUUID()).build();
  }

  @Override
  public void update(UUID customerId, CustomerDto customerDto) {
    // todo logic
  }

  @Override
  public void deleteById(UUID customerId) {
    log.debug("Deleting customer by Id...");
  }
}
