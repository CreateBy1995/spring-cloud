package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-01
 * @Description:
 */
@Component
@FeignClient(value = "nacos-provider-1",path = "/p1")
public interface EchoFeignClient {

    @GetMapping("/hello")
    String echoHello();
    @GetMapping("/userInfo")
    String userInfo();
}
