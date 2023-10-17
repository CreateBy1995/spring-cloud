package org.example.gateway.service.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-16
 * @Description:
 */
@Data
@Slf4j
@Component
public class ExecuteGlobalFilter implements GlobalFilter, Ordered {
    public ExecuteGlobalFilter(){
        log.info("ExecuteGlobalFilter init");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("ExecuteGlobalFilter execute start");
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    log.info("ExecuteGlobalFilter execute end");
                })
        );
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
