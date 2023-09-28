package org.example.service.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-08
 * @Description:
 */
@RestController
public class AuthController {
    @GetMapping("/echo")
    public String echo(){
        return "hello";
    }
//    @GetMapping("/oauth/confirm_access")
//    public String echo2(){
//        return "hello2";
//    }

}
