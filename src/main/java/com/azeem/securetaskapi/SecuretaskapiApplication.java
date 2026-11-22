package com.azeem.securetaskapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class SecuretaskapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuretaskapiApplication.class, args);
    }

}
