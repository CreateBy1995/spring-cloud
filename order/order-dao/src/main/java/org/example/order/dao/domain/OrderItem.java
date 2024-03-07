package org.example.order.dao.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@Data
public class OrderItem implements Serializable {
    private Long id;
    private Long orderId;
}
