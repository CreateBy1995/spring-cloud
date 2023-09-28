package org.example.gateway.service.configuration;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-28
 * @Description:
 */
@Configuration
public class GatewayConfiguration {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        // 路由构造器
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();



        StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
        config.setParts(1);

        GatewayFilter filter = new StripPrefixGatewayFilterFactory().apply(config);
        GatewayFilter finalFilter = new OrderedGatewayFilter(filter, 1);
        // 设置路径
        routes.route("pRule", r ->
                r.path("/provider/**").uri("lb://nacos-provider-1")
                        .filter(finalFilter)
        );
        routes.route("cRule", r ->
                r.path("/consumer/**").uri("lb://nacos-consumer-1")
        );

        return routes.build();
    }
}
