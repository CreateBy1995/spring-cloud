package pers.example.gateway.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pers.example.gateway.dao.domain.RouteRule;
import pers.example.gateway.service.service.IRouteRuleService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2024-08-23
 * @Description:
 */
@Slf4j
@Service(value = "configCenterRuleService")
@Data
@NacosPropertySource(dataId = "gateway-route", autoRefreshed = true)
public class ConfigCenterRuleServiceImpl implements IRouteRuleService {
    @Resource
    private ApplicationEventPublisher publisher;

    private List<RouteRule> routeRuleList = new ArrayList<>();
    private TypeReference<List<RouteRule>> typeReference = new TypeReference<List<RouteRule>>() {};

//    @Autowired
//    private ConfigService configService;
//
//    // 服务初始化时从 Nacos 加载配置
//    @PostConstruct
//    public void init() {
//        try {
//            String config = configService.getConfig("gateway-route", "DEFAULT_GROUP", 3000);
//            List<RouteRule> initialRouteRuleList = JSON.parseObject(config, typeReference);
//            synchronized (routeRuleList) {
//                if (initialRouteRuleList != null) {
//                    routeRuleList.clear();
//                    routeRuleList.addAll(initialRouteRuleList);
//                }
//            }
//            log.info("Initial route rules loaded: {}", routeRuleList);
//        } catch (Exception e) {
//            log.error("Failed to load initial route rules from Nacos", e);
//        }
//        publisher.publishEvent(new RefreshRoutesEvent(this));
//    }
    // 监听整个 JSON 配置的变化
    @NacosConfigListener(dataId = "gateway-route", timeout = 3000)
    public void onConfigChanged(String config) {
        List<RouteRule> newRouteRuleList = JSON.parseObject(config, typeReference);
        synchronized (routeRuleList) {
            if (newRouteRuleList != null) {
                routeRuleList.clear();
                routeRuleList.addAll(newRouteRuleList);
            }
        }
        publisher.publishEvent(new RefreshRoutesEvent(this));

    }


    @Override
    public List<RouteRule> listAll() {
        return routeRuleList;
    }

    @Override
    public int create(RouteRule routeRule) {
        return -1;
    }
}
