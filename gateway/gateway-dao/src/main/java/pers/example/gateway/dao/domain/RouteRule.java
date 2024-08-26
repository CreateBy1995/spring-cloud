package pers.example.gateway.dao.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Author: dongcx
 * @CreateTime: 2024-08-23
 * @Description:
 */
@Data
public class RouteRule {
    // 主键ID
    private Long id;
    // 规则ID
    private String ruleId;
    // 服务名称
    private String serviceId;
    // 映射路径
    private String uri;
    // 创建时间
    private Date createTime;
}
