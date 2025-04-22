package pers.example.gateway.service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pers.example.gateway.service.dto.RouteRuleDTO;
import pers.example.gateway.service.service.IRouteRuleService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-13
 * @Description: 实现RouteDefinitionRepository, 替换默认配置的InMemoryRouteDefinitionRepository
 */
@Component
public class DynamicRouteRepository implements RouteDefinitionRepository {
    @Autowired
    @Qualifier(value = "configCenterRuleService")
    private IRouteRuleService routeRuleService;
    private List<RouteDefinition> routeDefinitions = new ArrayList<>();
    @PostConstruct
    public void init() {
        List<RouteRuleDTO> routeRuleDTOList = routeRuleService.refresh();
        routeDefinitions = routeRuleDTOList.stream().map(this::convertRouteDefinition).collect(Collectors.toList());
    }

    private RouteDefinition convertRouteDefinition(RouteRuleDTO routeRuleDTO) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(routeRuleDTO.getRuleName());
        routeDefinition.setUri(URI.create(routeRuleDTO.getServiceName()));
        routeDefinition.getPredicates().add(new PredicateDefinition(routeRuleDTO.getMapping()));
        FilterDefinition stripPrefixFilter = new FilterDefinition("StripPrefix=1"); // 1表示要剥离一个路径部分
        routeDefinition.getFilters().add(stripPrefixFilter);
        routeDefinition.getFilters().addAll(convertFilterDefinition(routeRuleDTO.getFilterList()));
        return routeDefinition;
    }

    private List<FilterDefinition> convertFilterDefinition(List<String> filterNameList) {
        if (CollectionUtils.isEmpty(filterNameList)){
            return Collections.emptyList();
        }
        return filterNameList.stream().map(FilterDefinition::new).collect(Collectors.toList());
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        // 监听到RefreshRoutesEvent事件的时候会被回调，
        List<RouteRuleDTO> routeRuleDTOList = routeRuleService.refresh();
        routeDefinitions = routeRuleDTOList.stream().map(this::convertRouteDefinition).collect(Collectors.toList());
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
