package com.nttdatabc.mscustomer.service;

import static com.nttdatabc.mscustomer.utils.Constantes.EX_ERROR_PERSON_AUTH_SIGNER;
import static com.nttdatabc.mscustomer.utils.Constantes.EX_NOT_FOUND_RECURSO;
import static com.nttdatabc.mscustomer.utils.CustomerValidator.validateAuthorizedSignerEmpty;
import static com.nttdatabc.mscustomer.utils.CustomerValidator.validateAuthorizedSignerNoNulls;
import static com.nttdatabc.mscustomer.utils.CustomerValidator.validateAuthorizedSignerOnlyEmpresa;
import static com.nttdatabc.mscustomer.utils.CustomerValidator.validateCustomerEmpty;
import static com.nttdatabc.mscustomer.utils.CustomerValidator.validateCustomerNoNulls;
import static com.nttdatabc.mscustomer.utils.CustomerValidator.validateUserNotRegistred;
import static com.nttdatabc.mscustomer.utils.CustomerValidator.verifyTypePerson;
import static com.nttdatabc.mscustomer.utils.Utilitarios.generateUuid;

import com.nttdatabc.mscustomer.model.AuthorizedSigner;
import com.nttdatabc.mscustomer.model.Customer;
import com.nttdatabc.mscustomer.repository.CustomerRepository;
import com.nttdatabc.mscustomer.utils.exceptions.errors.ErrorResponseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Clase del CustomerServiceImpl.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public Flux<Customer> getAllCustomersService() {
    return customerRepository.findAll().switchIfEmpty(Flux.empty());
  }

  @Override
  public Mono<Void> createCustomerService(Customer customer) {
    return validateCustomerNoNulls(customer)
        .then(validateCustomerEmpty(customer))
        .then(verifyTypePerson(customer))
        .then(validateUserNotRegistred(customer.getIdentifier(), customerRepository))
        .then(validateAuthorizedSignerOnlyEmpresa(customer))
        .then(Mono.fromSupplier(() -> {
          customer.setId(generateUuid());
          return customer;
        }))
        .flatMap(customerRepository::save)
        .then();
  }

  @Override
  public Mono<Customer> getCustomerByIdService(String customerId) {
    return customerRepository.findById(customerId)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_NOT_FOUND_RECURSO,
            HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND)));
  }

  @Override
  public Mono<Void> updateCustomerService(Customer customer) {
    return validateCustomerNoNulls(customer)
        .then(validateCustomerEmpty(customer))
        .then(verifyTypePerson(customer))
        .then(validateUserNotRegistred(customer.getIdentifier(), customerRepository))
        .then(validateAuthorizedSignerOnlyEmpresa(customer))
        .then(Mono.just(customer))
        .flatMap(customerFlujo -> getCustomerByIdService(customerFlujo.getId()))
        .map(customerUpdate -> {
          customerUpdate.setAddress(customer.getAddress());
          customerUpdate.setBirthday(customer.getBirthday());
          customerUpdate.setEmail(customer.getEmail());
          customerUpdate.setFullname(customer.getFullname());
          customerUpdate.setPhone(customer.getPhone());
          customerUpdate.setAuthorizedSigners(customer.getAuthorizedSigners());
          return customerUpdate;
        })
        .flatMap(customerRepository::save)
        .then();
  }

  @Override
  public Mono<Void> deleteCustomerByIdService(String customerId) {
    return getCustomerByIdService(customerId)
        .flatMap(customerRepository::delete)
        .then();
  }

  @Override
  public Flux<AuthorizedSigner> getAuthorizedSignersByCustomerIdService(String customerId) {
    return getCustomerByIdService(customerId)
        .flux()
        .flatMap(customer -> {
          if (customer.getAuthorizedSigners() == null) {
            return Flux.error(new ErrorResponseException(EX_ERROR_PERSON_AUTH_SIGNER,
                HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
          } else {
            return Flux.fromIterable(customer.getAuthorizedSigners());
          }
        });
  }

  @Override
  public Mono<Void> createAuthorizedSignersByCustomerId(String customerId, AuthorizedSigner authorizedSigner) {
    return validateAuthorizedSignerNoNulls(authorizedSigner)
        .then(validateAuthorizedSignerEmpty(authorizedSigner))
        .then(getCustomerByIdService(customerId))
        .map(customer -> {
          List<AuthorizedSigner> existingSigners = customer.getAuthorizedSigners();
          if (Objects.isNull(existingSigners)) {
            existingSigners = new ArrayList<>();
          }
          existingSigners.add(authorizedSigner);
          customer.setAuthorizedSigners(existingSigners);
          return customer;
        })
        .flatMap(customerRepository::save)
        .then();
  }
}
