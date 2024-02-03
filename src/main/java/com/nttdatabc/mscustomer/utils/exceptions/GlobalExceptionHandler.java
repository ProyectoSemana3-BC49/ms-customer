package com.nttdatabc.mscustomer.utils.exceptions;

import com.nttdatabc.mscustomer.utils.exceptions.dto.ErrorDto;
import com.nttdatabc.mscustomer.utils.exceptions.errors.ErrorResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase Global.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
  /**
   * Metodo formado.
   *
   * @param e objeto de ErrorResponse.
   * @return el objeto de error formado.
   */
  @ExceptionHandler(value = ErrorResponseException.class)
  public ResponseEntity<ErrorDto> handleCustomException(ErrorResponseException e) {

    ErrorDto error = ErrorDto.builder()
        .httpStatus(e.getHttpStatus())
        .message(e.getMessage())
        .code(e.getStatus())
        .build();
    return new ResponseEntity<ErrorDto>(error, e.getHttpStatus());

  }
}