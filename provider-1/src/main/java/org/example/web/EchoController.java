package org.example.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.example.client.EchoFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-01
 * @Description:
 */
@RefreshScope// @Value更新后实时刷新
@RestController
@RequestMapping("/p1")
public class EchoController implements EchoFeignClient {
    @Value("${user.haha.name}")
    private String userName;

    @Value("${user.haha.age}")
    private Integer userAge;

    @Override
    public String echoHello() {
        return "p1 hello";
    }

    @Override
    @SentinelResource("userInfo22")
    public String userInfo() {
        return String.format("user info : name:%s ; age:%s", userName, userAge);
    }
}
