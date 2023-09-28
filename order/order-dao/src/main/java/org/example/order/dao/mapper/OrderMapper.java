package org.example.order.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.order.dao.domain.Order;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
public interface OrderMapper {
    Order getById(@Param("orderId") Long orderId);
}
