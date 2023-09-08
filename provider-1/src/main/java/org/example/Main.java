package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.Date;
import java.util.Optional;
@EnableOAuth2Sso
@SpringBootApplication
@EnableFeignClients
@EnableWebSecurity
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

}