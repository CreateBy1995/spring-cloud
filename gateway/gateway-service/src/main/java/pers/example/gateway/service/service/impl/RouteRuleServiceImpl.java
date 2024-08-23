package pers.example.gateway.service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.example.gateway.dao.domain.RouteRule;
import pers.example.gateway.dao.mapper.RouteRuleMapper;
import pers.example.gateway.service.service.IRouteRuleService;

import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2024-08-23
 * @Description:
 */
@Slf4j
@Service
public class RouteRuleServiceImpl implements IRouteRuleService {
    @Autowired
    private RouteRuleMapper mapper;

    @Override
    public List<RouteRule> listAll() {
        return mapper.listAll();
    }

    @Override
    public int create(RouteRule routeRule) {
        return mapper.create(routeRule);
    }
}
