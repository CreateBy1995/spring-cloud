package org.example.order.service.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.example.order.client.dto.OrderDTO;
import org.example.order.dao.domain.Order;
import org.example.order.dao.mapper.OrderItemMapper;
import org.example.order.dao.mapper.OrderMapper;
import org.example.order.service.utils.ReflectUtil;
import org.example.product.client.dto.ProductDTO;
import org.example.product.client.feignclient.ProductFeignClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
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
    private OrderItemMapper orderItemMapper;
    @Resource
    private ProductFeignClient productFeignClient;

    public OrderDTO getOrderDTO(Long orderId) {
        Order order = orderMapper.getById(orderId);
        return ReflectUtil.convert(order, OrderDTO.class);
    }
//    @GlobalTransactional
//    @ShardingSphereTransactionType(TransactionType.BASE)
//    @Transactional
    @Transactional
//    @ShardingSphereTransactionType(TransactionType.BASE)
    public void createByLocalTrans(){
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOrderId(4L);
//        List<OrderItem> orderItems = orderItemMapper.getByIdRange(1L);
//        System.out.println(orderItems.size());
//        orderItemMapper.create(orderItem);
//
//        List<Order> orders = orderMapper.getByIdRange(1L);
//        System.out.println(orders.size());
        List<Long> longList = Lists.newArrayList(2L,555L);
//        List<Order> orders = orderMapper.getByIdList(longList);
//        System.out.println(orders.size());
////
        List<Order> orders2 = orderMapper.getByIdListWithUserId(longList, 9L);
        System.out.println(orders2.size());
//        orderItems = orderItemMapper.getByIdRange(1L);
//        System.out.println(orderItems.size());
//        Order order = new Order();
//        order.setUserId(2L);
//        orderMapper.create(order);
//        System.out.println(10/0);
//        Order order2 = new Order();
//        order2.setUserId(999L);
//        orderMapper.create(order2);
//        orderItemMapper.getById(3L);
//        orderItemMapper.getByIdRange(2L);
//        orderItemMapper.getById(3L);

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
