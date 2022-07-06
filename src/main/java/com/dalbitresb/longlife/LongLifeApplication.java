package com.dalbitresb.longlife;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class LongLifeApplication {

  public static void main(String[] args) {
    SpringApplication.run(LongLifeApplication.class, args);
  }

}
