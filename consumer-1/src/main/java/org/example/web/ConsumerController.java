package org.example.web;

import org.example.client.EchoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
