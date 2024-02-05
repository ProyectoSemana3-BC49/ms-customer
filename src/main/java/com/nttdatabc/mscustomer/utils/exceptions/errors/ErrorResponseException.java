package com.nttdatabc.mscustomer.utils.exceptions.errors;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Clase del ErrorException.
 */
@Data
public class ErrorResponseException extends Exception {
  HttpStatus httpStatus;
  int status;

  /**
   * Constructor.
   *
   * @param messaage   mensaje que contendrá.
   * @param status     el código del statuts.
   * @param httpStatus el http status.
   */
  public ErrorResponseException(String messaage, int status, HttpStatus httpStatus) {
    super(messaage);
    this.httpStatus = httpStatus;
    this.status = status;
  }
}
