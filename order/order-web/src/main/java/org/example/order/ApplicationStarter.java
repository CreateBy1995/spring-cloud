package org.example.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan({"org.example.order.dao.mapper"})
public class ApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class);
    }
}