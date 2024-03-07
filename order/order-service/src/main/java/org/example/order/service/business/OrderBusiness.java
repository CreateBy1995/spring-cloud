package org.example.order.service.business;

import lombok.extern.slf4j.Slf4j;
import org.example.order.client.dto.OrderDTO;
import org.example.order.service.service.OrderService;
import org.example.product.client.dto.ProductDTO;
import org.example.product.client.feignclient.ProductFeignClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: dongcx
 * @CreateTime: 2023-10-26
 * @Description:
 */
@Slf4j
@Service
public class OrderBusiness {
    @Resource
    private OrderService orderService;
    @Resource
    private ProductFeignClient productFeignClient;

//    @GlobalTransactional
    public void doBusiness(){

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("草莓");
        productDTO.setPrice("4.6");
        productFeignClient.create(productDTO);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(2L);
        orderService.create(orderDTO);
    }




}
