package pers.example.gateway.service.vo;

import lombok.Data;

/**
 * @Author: dongcx
 * @CreateTime: 2024-08-23
 * @Description:
 */
@Data
public class RouteRuleVO {
    // 规则ID
    private String id;
    // 服务名称
    private String serviceId;
    // 映射路径
    private String uri;
    // 限流配置
    private LimitVO limitInfo;
}
