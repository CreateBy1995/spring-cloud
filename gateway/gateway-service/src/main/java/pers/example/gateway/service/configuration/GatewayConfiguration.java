package pers.example.gateway.service.configuration;

import pers.example.gateway.service.filter.LogGatewayFilterFactory;
import pers.example.gateway.service.filter.RequestTimeGatewayFilterFactory;
import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-28
 * @Description:
 */
@Configuration
public class GatewayConfiguration {

    /**
     * 自定义过滤器工厂类 注入之后 在gateway的自动配置类中会被使用
     * @return
     */
    @Bean
    @ConditionalOnEnabledFilter
    public RequestTimeGatewayFilterFactory requestTimeGatewayFilterFactory() {
        return new RequestTimeGatewayFilterFactory();
    }

    @Bean
    @ConditionalOnEnabledFilter
    public LogGatewayFilterFactory logGatewayFilterFactory() {
        return new LogGatewayFilterFactory();
    }
}
