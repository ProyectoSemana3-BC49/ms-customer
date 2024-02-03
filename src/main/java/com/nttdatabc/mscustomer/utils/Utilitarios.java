package com.nttdatabc.mscustomer.utils;

import java.util.UUID;

/**
 * Clase de utilitarios.
 */
public class Utilitarios {
  public static String generateUuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }


}
