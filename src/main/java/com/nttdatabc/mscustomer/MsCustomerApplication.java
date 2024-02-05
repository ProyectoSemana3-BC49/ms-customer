package com.nttdatabc.mscustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Clase entrypoint.
 */
@SpringBootApplication
@EnableEurekaClient
public class MsCustomerApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsCustomerApplication.class, args);
  }

}
