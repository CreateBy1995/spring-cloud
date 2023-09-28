package org.example.service.web;

import org.example.service.client.EchoFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-01
 * @Description:
 */
@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Resource
    private EchoFeignClient echoFeignClient;
    @GetMapping("/echo")
    public String echoAppName(){
        return  echoFeignClient.echoHello();
    }

    @GetMapping("/test")
    public String test(){
        return  "test112";
    }
}
