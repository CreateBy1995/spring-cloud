package org.example.product.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.product.dao.domain.Product;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
public interface ProductMapper {
    Product getById(@Param("productId") Long productId);
}
