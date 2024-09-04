package pers.example.product.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.example.product.web.service.ResourceService;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@RestController
@RequestMapping(value = "/echo")
public class EchoController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/number")
    @SentinelResource(value = "echo-resource1")
    public Integer echoNumber(Integer number) {
        return number;
    }

    @GetMapping("/number1")
    @SentinelResource(value = "echo-resource1")
    public Integer echoNumber1(Integer number) {
        return number;
    }

    @GetMapping("/string")
    @SentinelResource(value = "echo-resource2")
    public String echoString(String string) {
        return string;
    }

    @GetMapping("/getResource")
    public String getResource(@RequestParam("value") Integer value) {
        return resourceService.getResource(value);
    }
}
