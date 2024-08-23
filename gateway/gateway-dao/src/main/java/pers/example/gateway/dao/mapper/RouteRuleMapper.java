package pers.example.gateway.dao.mapper;

import pers.example.gateway.dao.domain.RouteRule;

import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
public interface RouteRuleMapper {
    List<RouteRule> listAll();

    int create(RouteRule routeRule);
}
