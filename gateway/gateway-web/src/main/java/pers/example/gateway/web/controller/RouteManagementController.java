package pers.example.gateway.web.controller;

import org.springframework.web.bind.annotation.*;
import pers.example.gateway.service.service.RouteManagementService;
import pers.example.gateway.service.vo.RouteRuleVO;

import javax.annotation.Resource;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-13
 * @Description:
 */
@RestController
@RequestMapping("/route")
public class RouteManagementController {
    @Resource
    private RouteManagementService routeManagementService;
    @PostMapping("/add")
    public String add(@RequestParam String name){
        routeManagementService.addRoute(name);
        return "ok";
    }

    @PostMapping("/addV2")
    public String addV2(@RequestBody RouteRuleVO ro){
        routeManagementService.addRoute(ro);
        return "ok";
    }
}
