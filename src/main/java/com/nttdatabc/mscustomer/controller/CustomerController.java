package com.nttdatabc.mscustomer.controller;

import static com.nttdatabc.mscustomer.utils.Constantes.PREFIX_PATH;

import com.nttdatabc.mscustomer.model.AuthorizedSigner;
import com.nttdatabc.mscustomer.model.Customer;
import com.nttdatabc.mscustomer.service.CustomerServiceImpl;
import com.nttdatabc.mscustomer.utils.exceptions.errors.ErrorResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Controller del Customer.
 */
@RestController
@Slf4j
@RequestMapping(PREFIX_PATH)
public class CustomerController implements CustomerControllerApi {

  @Autowired
  private CustomerServiceImpl customerService;

  @Override
  public ResponseEntity<Mono<Void>> createAuthorizedSignersByCustomerId(String customerId, AuthorizedSigner authorizedSigner, ServerWebExchange exchange) {
    return new ResponseEntity<>(customerService.createAuthorizedSignersByCustomerId(customerId, authorizedSigner)
        .doOnSubscribe(unused -> log.info("createAuthorizedSignersByCustomerId:: iniciando"))
        .doOnError(throwable -> log.error("createAuthorizedSignersByCustomerId:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createAuthorizedSignersByCustomerId:: finalizado con exito")), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> createCustomer(Customer customer, ServerWebExchange exchange) throws ErrorResponseException {
    return new ResponseEntity<>(customerService.createCustomerService(customer)
        .doOnSubscribe(unused -> log.info("createCustomer:: iniciando"))
        .doOnError(throwable -> log.error("createCustomer:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createCustomer:: finalizado con exito")),
        HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> deleteCustomerById(String customerId, ServerWebExchange exchange) {
    return new ResponseEntity<>(customerService.deleteCustomerByIdService(customerId)
        .doOnSubscribe(unused -> log.info("deleteCustomerById:: iniciando"))
        .doOnError(throwable -> log.error("deleteCustomerById:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("deleteCustomerById:: finalizado con exito")),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<Customer>> getAllCustomers(ServerWebExchange exchange) {
    return ResponseEntity.ok(
        customerService.getAllCustomersService()
            .doOnSubscribe(unused -> log.info("getAllCustomers:: iniciando"))
            .doOnError(throwable -> log.error("getAllCustomers:: error " + throwable.getMessage()))
            .doOnComplete(() -> log.info("getAllCustomers:: completadoo"))
    );
  }

  @Override
  public ResponseEntity<Flux<AuthorizedSigner>> getAuthorizedSignersByCustomerId(String customerId, ServerWebExchange exchange) {
    return new ResponseEntity<>(customerService.getAuthorizedSignersByCustomerIdService(customerId)
        .doOnSubscribe(unused -> log.info("getAuthorizedSignersByCustomerId:: iniciando"))
        .doOnError(throwable -> log.error("getAuthorizedSignersByCustomerId:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getAuthorizedSignersByCustomerId:: completadoo")), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Mono<Customer>> getCustomerById(String customerId, ServerWebExchange exchange) {
    return ResponseEntity.ok(
        customerService.getCustomerByIdService(customerId)
            .doOnSubscribe(unused -> log.info("getCustomerById:: iniciando"))
            .doOnError(throwable -> log.error("getCustomerById:: error " + throwable.getMessage()))
            .doOnSuccess((e) -> log.info("getCustomerById:: completadoo")));
  }

  @Override
  public ResponseEntity<Mono<Void>> updateCustomer(Customer customer, ServerWebExchange exchange) {
    return ResponseEntity.ok(
        customerService.updateCustomerService(customer)
            .doOnSubscribe(unused -> log.info("updateCustomer:: iniciando"))
            .doOnError(throwable -> log.error("updateCustomer:: error " + throwable.getMessage()))
            .doOnSuccess((e) -> log.info("updateCustomer:: completadoo")));
  }

}
