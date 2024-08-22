package pers.example.gateway.service.service;

import lombok.extern.slf4j.Slf4j;
import pers.example.gateway.service.configuration.DynamicRouteRepository;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-13
 * @Description:
 */
@Slf4j
@Service
public class RouteManagementService {
    // todo 替换成数据源
    @Resource
    private DynamicRouteRepository routeRepository;
    @Resource
    private ApplicationEventPublisher publisher;
    public void addRoute(String name){
        RouteDefinition routeDefinition = new RouteDefinition();
        if ("o".equals(name)){
            routeDefinition.setId("oRule");  // 设置路由规则的ID
            routeDefinition.setUri(URI.create("lb://cloud-order"));  // 设置目标URI
            routeDefinition.getPredicates().add(new PredicateDefinition("Path=/api/order/**"));  // 设置路径匹配断言
            FilterDefinition stripPrefixFilter = new FilterDefinition("StripPrefix=1"); // 1表示要剥离一个路径部分
            FilterDefinition requestTimeFilter = new FilterDefinition("RequestTime=31,243");
            routeDefinition.getFilters().add(stripPrefixFilter);
            routeDefinition.getFilters().add(requestTimeFilter);
        }
        if ("p".equals(name)){
            routeDefinition.setId("pRule");  // 设置路由规则的ID
            routeDefinition.setUri(URI.create("lb://cloud-product"));  // 设置目标URI
            routeDefinition.getPredicates().add(new PredicateDefinition("Path=/api/product/**"));  // 设置路径匹配断言
            FilterDefinition stripPrefixFilter = new FilterDefinition("StripPrefix=1"); // 1表示要剥离一个路径部分
            routeDefinition.getFilters().add(stripPrefixFilter);
        }
        routeRepository.save(Mono.just(routeDefinition));
    }


    public void clear(){
        routeRepository.clear();
    }
}
