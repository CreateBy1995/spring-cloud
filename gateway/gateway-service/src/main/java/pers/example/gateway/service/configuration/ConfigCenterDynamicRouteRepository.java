package pers.example.gateway.service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;
import pers.example.gateway.dao.domain.RouteRule;
import pers.example.gateway.service.service.IRouteRuleService;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-13
 * @Description: mysql
 */
@Component(value = "configCenterDynamicRouteRepository")
public class ConfigCenterDynamicRouteRepository extends AbstractDynamicRouteRepository {
    @Autowired
    @Qualifier(value = "configCenterRuleService")
    private IRouteRuleService routeRuleService;
//    @Override
//    public List<RouteDefinition> routeListInitial() {
//        List<RouteRule> routeRules = routeRuleService.listAll();
//        return routeRules.stream().map(this::convertRouteDefinition).collect(Collectors.toList());
//    }

    private RouteDefinition convertRouteDefinition(RouteRule routeRule) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(routeRule.getRuleId());
        routeDefinition.setUri(URI.create(routeRule.getServiceId()));
        routeDefinition.getPredicates().add(new PredicateDefinition(routeRule.getUri()));
        FilterDefinition stripPrefixFilter = new FilterDefinition("StripPrefix=1"); // 1表示要剥离一个路径部分
        routeDefinition.getFilters().add(stripPrefixFilter);
        return routeDefinition;
    }

    @Override
    public List<RouteDefinition> routeListRefresh() {
        List<RouteRule> routeRules = routeRuleService.listAll();
        return routeRules.stream().map(this::convertRouteDefinition).collect(Collectors.toList());
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
