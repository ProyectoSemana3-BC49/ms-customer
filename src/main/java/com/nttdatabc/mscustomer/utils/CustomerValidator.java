package com.nttdatabc.mscustomer.utils;

import static com.nttdatabc.mscustomer.utils.Constantes.EX_ERROR_PERSON_AUTH_SIGNER;
import static com.nttdatabc.mscustomer.utils.Constantes.EX_ERROR_REQUEST;
import static com.nttdatabc.mscustomer.utils.Constantes.EX_ERROR_TYPE_PERSONA;
import static com.nttdatabc.mscustomer.utils.Constantes.EX_USER_REGISTRED;
import static com.nttdatabc.mscustomer.utils.Constantes.EX_VALUE_EMPTY;

import com.nttdatabc.mscustomer.model.AuthorizedSigner;
import com.nttdatabc.mscustomer.model.Customer;
import com.nttdatabc.mscustomer.model.TypeCustomer;
import com.nttdatabc.mscustomer.repository.CustomerRepository;
import com.nttdatabc.mscustomer.utils.exceptions.errors.ErrorResponseException;
import java.util.function.Predicate;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

/**
 * Clase para las validaciones del input del Customer.
 */
public class CustomerValidator {
  /**
   * Valida que los valores no vengan nulos.
   *
   * @param customer el input.
   * @throws ErrorResponseException excepcion que se dispara.
   */
  public static Mono<Void> validateCustomerNoNulls(Customer customer)  {
    return Mono.just(customer)
        .filter(c -> c.getIdentifier() != null)
        .filter(c -> c.getFullname() != null)
        .filter(c -> c.getType() != null)
        .filter(c -> c.getAddress() != null)
        .filter(c -> c.getPhone() != null)
        .filter(c -> c.getEmail() != null)
        .filter(c -> c.getBirthday() != null)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_REQUEST,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Valida que los valores no vengan vacíos.
   *
   * @param customer el input.
   * @throws ErrorResponseException excepcion que se dispara.
   */
  public static Mono<Void> validateCustomerEmpty(Customer customer) {
    return Mono.just(customer)
        .filter(c -> !c.getIdentifier().isBlank())
        .filter(c -> !c.getFullname().isBlank())
        .filter(c -> !c.getType().isBlank())
        .filter(c -> !c.getAddress().isBlank())
        .filter(c -> !c.getPhone().isBlank())
        .filter(c -> !c.getEmail().isBlank())
        .filter(c -> !c.getBirthday().isBlank())
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_VALUE_EMPTY,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Verifica que el tipo de persona sea PERSONA | EMPRESA.
   *
   * @param customer input.
   * @throws ErrorResponseException error que se dispara.
   */
  public static Mono<Void> verifyTypePerson(Customer customer) {
    Predicate<Customer> existTypePerson = customerValidate -> customerValidate
        .getType()
        .equalsIgnoreCase(TypeCustomer.PERSONA.toString())
        || customerValidate.getType().equalsIgnoreCase(TypeCustomer.EMPRESA.toString());
    if (existTypePerson.negate().test(customer)) {
      return Mono.error(new ErrorResponseException(EX_ERROR_TYPE_PERSONA,
          HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST));
    }
    return Mono.empty();
  }

  /**
   * Valida que el usuario no este registrado, para poder registrarlo.
   *
   * @param customerId         id del customer
   * @param customerRepository clase del repositorio
   * @throws ErrorResponseException error que se dispara.
   */
  public static Mono<Void> validateUserNotRegistred(String customerId, CustomerRepository customerRepository)  {
    return customerRepository.findByIdentifier(customerId)
        .flatMap(customer -> Mono.error(new ErrorResponseException(EX_USER_REGISTRED, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Valida inputs del authorized.
   *
   * @param authorizedSigner input.
   * @throws ErrorResponseException error que se dispara.
   */
  public static Mono<Void> validateAuthorizedSignerNoNulls(AuthorizedSigner authorizedSigner) {
    return Mono.just(authorizedSigner)
        .filter(c -> c.getCargo() != null)
        .filter(c -> c.getFullname() != null)
        .filter(c -> c.getDni() != null)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_REQUEST,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Validar que el authorized no venga vacio.
   *
   * @param authorizedSigner input.
   * @throws ErrorResponseException error que dispara.
   */
  public static Mono<Void> validateAuthorizedSignerEmpty(AuthorizedSigner authorizedSigner)  {
    return Mono.just(authorizedSigner)
        .filter(c -> !c.getCargo().isBlank())
        .filter(c -> !c.getFullname().isBlank())
        .filter(c -> !c.getDni().isBlank())
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_VALUE_EMPTY,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Validar que persona no tenga authorized.
   *
   * @param customer input.
   * @throws ErrorResponseException error que dispara.
   */
  public static Mono<Void> validateAuthorizedSignerOnlyEmpresa(Customer customer)  {
    if (customer.getType().equalsIgnoreCase(TypeCustomer.PERSONA.toString())
        && customer.getAuthorizedSigners() != null) {
      return Mono.error(new ErrorResponseException(EX_ERROR_PERSON_AUTH_SIGNER,
          HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST));
    }
    return Mono.empty();
  }
}
