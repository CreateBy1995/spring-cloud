package org.example;

import org.example.web.EchoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Date;
import java.util.Optional;
@SpringBootApplication
//@EnableFeignClients
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

}