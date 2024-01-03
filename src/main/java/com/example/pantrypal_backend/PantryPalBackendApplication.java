package com.example.pantrypal_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class PantryPalBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PantryPalBackendApplication.class, args);
    }

}
