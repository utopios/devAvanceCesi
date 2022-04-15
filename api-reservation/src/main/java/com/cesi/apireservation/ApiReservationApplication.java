package com.cesi.apireservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class ApiReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiReservationApplication.class, args);
    }

}
