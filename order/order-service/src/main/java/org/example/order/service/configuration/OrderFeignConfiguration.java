package org.example.order.service.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-28
 * @Description:
 */

@Configuration
@EnableFeignClients(basePackages = {"org.example"})
public class OrderFeignConfiguration {
}
