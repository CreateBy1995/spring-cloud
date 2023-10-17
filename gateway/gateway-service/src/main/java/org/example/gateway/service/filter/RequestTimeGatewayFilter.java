package org.example.gateway.service.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-16
 * @Description:
 */
@Data
@Slf4j
public class RequestTimeGatewayFilter implements GatewayFilter, Ordered {

    private String field1;
    private int field2;
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    public RequestTimeGatewayFilter(RequestTimeGatewayFilterFactory.Config config){
        field1 = config.getField1();
        field2 = config.getField2();
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("RequestTimeGatewayFilter config, f1:{}, f2:{}", field1,
                field2);
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
