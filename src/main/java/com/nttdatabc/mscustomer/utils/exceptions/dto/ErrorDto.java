package com.nttdatabc.mscustomer.utils.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Clase DTO del Error.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
  private HttpStatus httpStatus;
  private String message;
  private int code;
}
