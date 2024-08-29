package pers.example.product.service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.example.product.client.dto.ProductDTO;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@Slf4j
@Service
public class ProductService {

    public ProductDTO getProductDTO(Long productId){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("name " + productId);
        productDTO.setId(productId);
        return productDTO;
    }

//    @GlobalTransactional
    public boolean create(ProductDTO productDTO){
        return false;
    }
}
