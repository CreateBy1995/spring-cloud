package pers.example.gateway.dao.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Author: dongcx
 * @CreateTime: 2024-08-23
 * @Description:
 */
@Data
public class LimitInfo {
    // 主键ID
    private Long id;
    // 规则ID
    private String ruleId;
    // 窗口大小
    private Integer windowSize;
    // 访问次数
    private Integer count;
    // 创建时间
    private Date createTime;
}
