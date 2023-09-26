package org.example.client.feignclient;

import org.example.client.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@Component
@FeignClient(value = "cloud-product",path = "/product")
public interface ProductFeignClient {

    @GetMapping("/getProductById")
    ProductDTO getProductById(@RequestParam(value = "productId") Long productId);
}
