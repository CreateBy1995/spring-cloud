//package pers.example.gateway.service.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.filter.FilterDefinition;
//import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.stereotype.Service;
//import pers.example.gateway.dao.domain.RouteRule;
//import pers.example.gateway.service.configuration.AbstractDynamicRouteRepository;
//import pers.example.gateway.service.util.ReflectUtil;
//import pers.example.gateway.service.vo.RouteRuleVO;
//
//import javax.annotation.Resource;
//import java.net.URI;
//
///**
// * @Author: dongcx
// * @CreateTime: 2023-10-13
// * @Description:
// */
//@Slf4j
//@Service
//public class RouteManagementService {
//    // todo 替换成数据源
//    @Autowired
//    @Qualifier(value = "configCenterDynamicRouteRepository")
//    private AbstractDynamicRouteRepository dynamicRouteRepository;
//
//    @Autowired
//    private IRouteRuleService routeRuleService;
//    @Resource
//    private ApplicationEventPublisher publisher;
//    public void addRoute(String name){
//        RouteDefinition routeDefinition = new RouteDefinition();
//        if ("o".equals(name)){
//            routeDefinition.setId("oRule");  // 设置路由规则的ID
//            routeDefinition.setUri(URI.create("lb://cloud-order"));  // 设置目标URI
//            routeDefinition.getPredicates().add(new PredicateDefinition("Path=/api/order/**"));  // 设置路径匹配断言
//            FilterDefinition stripPrefixFilter = new FilterDefinition("StripPrefix=1"); // 1表示要剥离一个路径部分
//            FilterDefinition requestTimeFilter = new FilterDefinition("RequestTime=31,243");
//            routeDefinition.getFilters().add(stripPrefixFilter);
//            routeDefinition.getFilters().add(requestTimeFilter);
//        }
//        if ("p".equals(name)){
//            routeDefinition.setId("pRule");  // 设置路由规则的ID
//            routeDefinition.setUri(URI.create("lb://cloud-product"));  // 设置目标URI
//            routeDefinition.getPredicates().add(new PredicateDefinition("Path=/api/product/**"));  // 设置路径匹配断言
//            FilterDefinition stripPrefixFilter = new FilterDefinition("StripPrefix=1"); // 1表示要剥离一个路径部分
//            FilterDefinition requestTimeFilter = new FilterDefinition("RequestTime=31,243");
//            routeDefinition.getFilters().add(requestTimeFilter);
//            routeDefinition.getFilters().add(stripPrefixFilter);
//        }
//    }
//
//    public void addRoute(RouteRuleVO ro){
//        RouteRule routeRule = ReflectUtil.convert(ro, RouteRule.class);
//        publisher.publishEvent(new RefreshRoutesEvent(this));
//    }
//
//}
