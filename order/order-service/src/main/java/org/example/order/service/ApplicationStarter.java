package org.example.order.service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: dongcx
 * @CreateTime: 2024-08-22
 * @Description:
 */

//@EnableTransactionManagement
@EnableDubbo
@SpringBootApplication
//@MapperScan({"pers.example.product.dao.mapper"})
public class ApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class);
    }
}
