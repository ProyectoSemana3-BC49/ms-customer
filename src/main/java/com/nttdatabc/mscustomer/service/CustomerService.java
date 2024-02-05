package com.nttdatabc.mscustomer.service;

import com.nttdatabc.mscustomer.model.AuthorizedSigner;
import com.nttdatabc.mscustomer.model.Customer;
import com.nttdatabc.mscustomer.utils.exceptions.errors.ErrorResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface de CustomerService.
 */
public interface CustomerService {
  Flux<Customer> getAllCustomersService();

  Mono<Void> createCustomerService(Customer customer) throws ErrorResponseException;

  Mono<Customer> getCustomerByIdService(String customerId) throws ErrorResponseException;

  Mono<Void> updateCustomerService(Customer customer) throws ErrorResponseException;

  Mono<Void> deleteCustomerByIdService(String customerId) throws ErrorResponseException;

  Flux<AuthorizedSigner> getAuthorizedSignersByCustomerIdService(String customerId) throws ErrorResponseException;

  Mono<Void> createAuthorizedSignersByCustomerId(String customerId, AuthorizedSigner authorizedSigner) throws ErrorResponseException;
}
