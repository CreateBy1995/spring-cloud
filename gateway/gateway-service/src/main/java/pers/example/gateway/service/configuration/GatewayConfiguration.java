package pers.example.gateway.service.configuration;

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
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
//        // 路由构造器
//        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//
//
//
//        // 转发请求前，过滤前N个/前的字符 例如 N=1 则/api/order/ -> /order/
//        StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
//        config.setParts(1);
//
//        GatewayFilter filter = new StripPrefixGatewayFilterFactory().apply(config);
//        GatewayFilter finalFilter = new OrderedGatewayFilter(filter, 1);
//        // 设置路径
//        routes.route("pRule", r ->
//                r.path("/api/product/**").uri("lb://cloud-product")
//                        .filter(finalFilter)
//        );
//        routes.route("cRule", r ->
//                r.path("/api/order/**").uri("lb://cloud-order")
//                        .filter(finalFilter)
//        );
//
//        return routes.build();
//    }
//    @Bean
//    public RouteDefinitionRepository dynamicRouteRepository(){
//        return new DynamicRouteRepository();
//    }
}
