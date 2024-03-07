package org.example.order.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.order.dao.domain.OrderItem;

import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
public interface OrderItemMapper {
    OrderItem getById(@Param("orderItemId") Long orderItemId);

    List<OrderItem> getByIdRange(@Param("orderItemId") Long orderItemId);

    int create(OrderItem orderItem);

}
