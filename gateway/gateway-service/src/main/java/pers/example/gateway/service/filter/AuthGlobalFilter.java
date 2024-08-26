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
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        log.info("auth verify start");
        // todo auth verify
        if (StringUtils.isBlank(auth)) {
            log.error("auth verify error");
            return Mono.error(new RuntimeException());
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
