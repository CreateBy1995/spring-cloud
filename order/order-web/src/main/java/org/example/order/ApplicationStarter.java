package org.example.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
@MapperScan({"org.example.order.dao.mapper"})
public class ApplicationStarter {
    public static void main(String[] args) {
        var ioc =  SpringApplication.run(ApplicationStarter.class);
    }
}