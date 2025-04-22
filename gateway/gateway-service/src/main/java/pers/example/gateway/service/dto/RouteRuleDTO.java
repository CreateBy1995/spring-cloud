package pers.example.gateway.service.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2024-08-28
 * @Description:
 */
@Data
public class RouteRuleDTO {
    // 规则名称
    private String ruleName;
    // 映射服务
    private String serviceName;
    // 映射路径
    private String mapping;
    // 过滤器列表
    private List<String> filterList;
}
