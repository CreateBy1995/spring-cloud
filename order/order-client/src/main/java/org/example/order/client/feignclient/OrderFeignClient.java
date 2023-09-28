package org.example.order.client.feignclient;

import org.example.order.client.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@FeignClient(value = "cloud-order",path = "/order")
public interface OrderFeignClient {

    @GetMapping("/getOrderById")
    OrderDTO getOrderById(@RequestParam(value = "orderId") Long orderId);

    @GetMapping("/getOrderAndProduct")
    String getOrderAndProduct(@RequestParam(value = "id") Long id);
}
