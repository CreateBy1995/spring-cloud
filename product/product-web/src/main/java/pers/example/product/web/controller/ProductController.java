package pers.example.product.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.example.product.client.dto.ProductDTO;
import pers.example.product.client.feignclient.ProductFeignClient;
import pers.example.product.service.service.ProductService;

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
    @SentinelResource(value = "product-resource-read")
    public ProductDTO getProductById(Long productId) {
        return productService.getProductDTO(productId);
    }

    @Override
    @SentinelResource(value = "product-resource-write")
    public Boolean create(ProductDTO productDTO) {
        return productService.create(productDTO);
    }
}
