package org.example.order.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.order.dao.domain.Order;

import java.util.List;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
public interface OrderMapper {
    Order getById(@Param("orderId") Long orderId);

    List<Order> getByIdRange(@Param("orderId") Long orderId);

    List<Order> getByIdList(@Param("orderIdList") List<Long> orderIdList);

    List<Order> getByIdListWithUserId(@Param("orderIdList") List<Long> orderIdList,
                                      @Param("userId") Long userId);



    int create(Order order);
}
