package org.example.order.service.service;

import lombok.extern.slf4j.Slf4j;
import org.example.order.client.dto.OrderDTO;
import org.example.order.dao.domain.Order;
import org.example.order.dao.mapper.OrderMapper;
import org.example.order.service.utils.ReflectUtil;
import org.example.product.client.dto.ProductDTO;
import org.example.product.client.feignclient.ProductFeignClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@Slf4j
@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ProductFeignClient productFeignClient;

    public OrderDTO getOrderDTO(Long orderId) {
        Order order = orderMapper.getById(orderId);
        return ReflectUtil.convert(order, OrderDTO.class);
    }

    public String getOrderAndProduct(Long id) {
        OrderDTO orderDTO = getOrderDTO(id);
        ProductDTO productDTO = productFeignClient.getProductById(id);

        return String.format("oderId:%s, productId:%s", Optional.ofNullable(orderDTO).map(OrderDTO::getId).orElse(-1L),
                Optional.ofNullable(productDTO).map(ProductDTO::getId).orElse(-1L));
    }

    public void create(OrderDTO orderDTO){
        if (orderDTO.getUserId() >0){
            throw new RuntimeException("test user id ");
        }
        Order order = ReflectUtil.convert(orderDTO, Order.class);
        orderMapper.create(order);

    }
}
