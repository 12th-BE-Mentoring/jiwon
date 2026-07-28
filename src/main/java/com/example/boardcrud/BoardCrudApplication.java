package com.example.boardcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan; // import 추가!

@ConfigurationPropertiesScan // <--- 바로 여기에 붙여주는 겁니다!
@SpringBootApplication
public class BoardCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardCrudApplication.class, args);
    }

}