package pers.example.gateway.service.filter;

//import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.google.common.collect.Lists;
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
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";


    public static final String FIELD1_KEY = "field1";

    public static final String FIELD2_KEY = "field2";

    /**
     * URL key.
     */
    public static final String URL_KEY = "url";
    public RequestTimeGatewayFilterFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Lists.newArrayList(FIELD1_KEY, FIELD2_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new RequestTimeGatewayFilter(config);
    }
    @Data
    public static class Config {
        private String field1;
        private int field2;
    }
}
