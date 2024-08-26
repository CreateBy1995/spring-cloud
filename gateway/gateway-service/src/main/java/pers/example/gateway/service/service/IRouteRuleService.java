package pers.example.gateway.service.service;

import pers.example.gateway.dao.domain.RouteRule;

import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2024-08-23
 * @Description:
 */
public interface IRouteRuleService {
    List<RouteRule> listAll();

    int create(RouteRule routeRule);
}
