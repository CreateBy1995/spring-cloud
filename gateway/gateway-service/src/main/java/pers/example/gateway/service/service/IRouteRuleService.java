package pers.example.gateway.service.service;

import pers.example.gateway.dao.domain.RouteRule;
import pers.example.gateway.service.dto.RouteRuleDTO;

import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2024-08-23
 * @Description: 路由规则管理接口
 */
public interface IRouteRuleService {
    List<RouteRuleDTO> refresh();
}
