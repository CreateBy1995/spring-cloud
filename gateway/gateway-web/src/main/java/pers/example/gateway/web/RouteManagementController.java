package pers.example.gateway.web;

import pers.example.gateway.service.service.RouteManagementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/clear")
    public String clear(){
        routeManagementService.clear();
        return "ok";
    }
}
