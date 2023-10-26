package org.example.order.web.controller;

import org.example.order.client.dto.OrderDTO;
import org.example.order.client.feignclient.OrderFeignClient;
import org.example.order.service.business.OrderBusiness;
import org.example.order.service.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController implements OrderFeignClient {
    @Resource
    private OrderService orderService;
    @Resource
    private OrderBusiness orderBusiness;
    @Override
    public OrderDTO getOrderById(Long orderId) {
        return orderService.getOrderDTO(orderId);
    }

    @Override
    public String getOrderAndProduct(Long id) {
        return orderService.getOrderAndProduct(id);
    }

    @Override
    public String doBusiness() {
        orderBusiness.doBusiness();
        return null;
    }

}
