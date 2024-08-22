package pers.example.gateway.service.configuration;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-13
 * @Description: 实现RouteDefinitionRepository, 替换默认配置的InMemoryRouteDefinitionRepository
 */
@Component
public class DynamicRouteRepository implements RouteDefinitionRepository {
    @Resource
    private ApplicationEventPublisher publisher;
    private final List<RouteDefinition> routeDefinitions = new ArrayList<>();

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        // 监听到RefreshRoutesEvent事件的时候会被回调，
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        // 添加路由定义，并且发布RefreshRoutesEvent  CachingRouteLocator会监听这个事件 最终会回调getRouteDefinitions方法
        routeDefinitions.add(route.block());
        publisher.publishEvent(new RefreshRoutesEvent(this));
        return Mono.empty();
    }
    public void clear(){
        routeDefinitions.clear();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }



    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return Mono.defer(() -> Mono.error(new NotFoundException("Unsupported operation")));
    }
}
