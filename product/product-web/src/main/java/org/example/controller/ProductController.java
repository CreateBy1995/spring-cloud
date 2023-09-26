package org.example.controller;

import org.example.client.dto.ProductDTO;
import org.example.client.feignclient.ProductFeignClient;
import org.example.service.service.ProductService;
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
