package pers.example.gateway.service.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
//import org.apache.http.auth.AuthenticationException;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
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
public class LogFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("request is {}", exchange.getRequest());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    log.info("response status: {}", exchange.getResponse().getStatusCode());
                })
        );
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 1;
    }
}
