package org.example.product.dao.domain;

import lombok.Data;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@Data
public class Product {
    private Long id;
    private String productName;
    private String price;
}
