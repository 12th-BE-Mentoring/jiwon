package com.example.boardcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan; // import 추가!

@ConfigurationPropertiesScan
@SpringBootApplication
public class BoardCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardCrudApplication.class, args);
    }

}