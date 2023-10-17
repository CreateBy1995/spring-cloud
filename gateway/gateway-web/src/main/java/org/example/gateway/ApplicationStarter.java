package org.example.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"org.example.gateway.dao.mapper"})
public class ApplicationStarter {
    public static void main(String[] args) {
//        System.out.println(NameUtils.normalizeFilterFactoryName(RequestTimeGatewayFilterFactory.class));
        SpringApplication.run(ApplicationStarter.class);
    }
}