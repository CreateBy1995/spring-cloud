package pers.example.gateway.service.filter;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-16
 * @Description:
 */
@Slf4j
public class LimitGatewayFilterFactory extends AbstractGatewayFilterFactory<LimitGatewayFilterFactory.Config> {
    public static final String WINDOW_SIZE_KEY = "windowSize";

    public static final String COUNT_KEY = "count";

    public LimitGatewayFilterFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Lists.newArrayList(WINDOW_SIZE_KEY, COUNT_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new LimitGatewayFilter(config);
    }
    @Data
    public static class Config {
        // 窗口大小 单位：分钟
        private Integer windowSize;
        // 调用次数
        private Integer count;
    }
}
