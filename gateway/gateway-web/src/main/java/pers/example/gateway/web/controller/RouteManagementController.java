package pers.example.gateway.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-13
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/route")
public class RouteManagementController {

    @PostMapping("/add")
    public String add(@RequestParam String name){
        return "ok";
    }

}
