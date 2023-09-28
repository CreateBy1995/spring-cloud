package org.example.service.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-08
 * @Description:
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "client2 test";
    }
}
