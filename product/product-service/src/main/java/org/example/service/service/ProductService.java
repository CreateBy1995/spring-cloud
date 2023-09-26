package org.example.service.service;

import lombok.extern.slf4j.Slf4j;
import org.example.client.dto.ProductDTO;
import org.example.domain.Product;
import org.example.mapper.ProductMapper;
import org.example.service.utils.ReflectUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@Slf4j
@Service
public class ProductService {
    @Resource
    private ProductMapper productMapper;

    public ProductDTO getProductDTO(Long productId){
        Product product = productMapper.getById(productId);
        return ReflectUtil.convert(product, ProductDTO.class);
    }
}
