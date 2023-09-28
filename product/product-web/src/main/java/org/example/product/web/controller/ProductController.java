package org.example.product.web.controller;

import org.example.product.client.dto.ProductDTO;
import org.example.product.client.feignclient.ProductFeignClient;
import org.example.product.service.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController implements ProductFeignClient {
    @Resource
    private ProductService productService;

    @Override
    public ProductDTO getProductById(Long productId) {
        return productService.getProductDTO(productId);
    }
}
