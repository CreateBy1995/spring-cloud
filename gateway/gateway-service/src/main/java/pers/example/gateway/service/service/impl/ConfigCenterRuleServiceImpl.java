package pers.example.gateway.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pers.example.gateway.service.dto.RouteRuleDTO;
import pers.example.gateway.service.service.IRouteRuleService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
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

    private List<RouteRuleDTO> routeRuleList = new ArrayList<>();
    private TypeReference<List<RouteRuleDTO>> typeReference = new TypeReference<List<RouteRuleDTO>>() {};

//    @Autowired
//    private ConfigService configService; // 需要注入 Nacos 的 ConfigService
//    @PostConstruct
//    public void init() {
//        // 初始化时加载配置到routeRuleList
//        updateRouteRules(initialConfig);
//    }

    // 监听整个 JSON 配置的变化
    @NacosConfigListener(dataId = "gateway-route", timeout = 3000)
    public void onConfigChanged(String config) {
        updateRouteRules(config);
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    private void updateRouteRules(String config) {
        List<RouteRuleDTO> newRouteRuleList = JSON.parseObject(config, typeReference);
        synchronized (routeRuleList) {
            if (newRouteRuleList != null) {
                routeRuleList.clear();
                routeRuleList.addAll(newRouteRuleList);
            }
        }
    }
    @Override
    public List<RouteRuleDTO> refresh() {
        return routeRuleList;
    }
}
