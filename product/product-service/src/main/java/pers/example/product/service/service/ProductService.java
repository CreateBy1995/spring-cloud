package pers.example.product.service.service;

import lombok.extern.slf4j.Slf4j;
import pers.example.product.client.dto.ProductDTO;
import pers.example.product.dao.domain.Product;
import pers.example.product.dao.mapper.ProductMapper;
import pers.example.product.service.utils.ReflectUtil;
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

//    @GlobalTransactional
    public boolean create(ProductDTO productDTO){
        Product product = ReflectUtil.convert(productDTO, Product.class);
        return productMapper.create(product) > 0;
    }
}
