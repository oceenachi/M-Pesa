package com.oaf.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MPesaApplication {

    @Bean
    ModelMapper ModelMapper(){return new ModelMapper();}

//    @Bean

    public static void main(String[] args) {
        SpringApplication.run(MPesaApplication.class, args);
    }

}
