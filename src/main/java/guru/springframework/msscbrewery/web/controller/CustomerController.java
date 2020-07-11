package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import guru.springframework.msscbrewery.web.services.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Luis M. Kupferberg Ruiz (lkupferberg@overactive.com)
 * @created 2020-07-04 02:18
 */
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

  private CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping({"/{customerId}"})
  public ResponseEntity<CustomerDto> getById(@PathVariable("customerId") UUID customerId) {

    return new ResponseEntity(customerService.getById(customerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity create(@Valid @RequestBody CustomerDto customerDto) {
    CustomerDto createdCustomer = customerService.create(customerDto);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Location", "/api/v1/customer" + createdCustomer.getId().toString());

    return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
  }

  @PutMapping({"/{customerId}"})
  public ResponseEntity update(
      @PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerDto customerDto) {
    customerService.update(customerId, customerDto);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping({"/{customerId}"})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable("customerId") UUID customerId) {
    customerService.deleteById(customerId);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
    List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

    e.getConstraintViolations()
        .forEach(
            constraintViolation -> {
              errors.add(
                  constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
            });

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
