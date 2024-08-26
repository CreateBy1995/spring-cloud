package pers.example.gateway.service.configuration;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-13
 * @Description: 实现RouteDefinitionRepository, 替换默认配置的InMemoryRouteDefinitionRepository
 */
public abstract class AbstractDynamicRouteRepository implements RouteDefinitionRepository {
    private List<RouteDefinition> routeDefinitions = new ArrayList<>();

    @PostConstruct
    public void init() {
        routeDefinitions = routeListRefresh();
    }

    // init routeDefinitions
//    public abstract List<RouteDefinition> routeListInitial();

    public abstract List<RouteDefinition> routeListRefresh();


    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        // 监听到RefreshRoutesEvent事件的时候会被回调，
        routeDefinitions = routeListRefresh();
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return Mono.empty();
    }

}
