package pers.example.gateway;

import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableNacosConfig
//@MapperScan({"pers.example.gateway.dao.mapper"})
public class ApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class);
    }
}