package pers.example.gateway.service.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-16
 * @Description:
 */
@Data
@Slf4j
public class LimitGatewayFilter implements GatewayFilter, Ordered {
    // todo 修改为接入sentinel
    private Integer windowSize;
    private Integer count;

    public LimitGatewayFilter(LimitGatewayFilterFactory.Config config) {
        windowSize = config.getWindowSize();
        count = config.getCount();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("限流过滤器开始执行");
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        if (Objects.isNull(route)){
            return chain.filter(exchange);
        }
        if (exceedWindowSize(route.getId())){
            log.error("超出限流控制");
            return Mono.error(new IllegalAccessError("超出限流控制"));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }


    private boolean exceedWindowSize(String routeKey) {
        // 通过routeKey到redis中查询时间窗口内的访问次数
        int currentCount = RandomUtils.nextInt(10);
        return currentCount >= count;
    }
}
