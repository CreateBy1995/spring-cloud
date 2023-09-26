package org.example.client.dto;

import lombok.Data;

/**
 * @Author: dongcx
 * @CreateTime: 2023-09-26
 * @Description:
 */
@Data
public class ProductDTO {
    private Long id;
    private String productName;
    private String price;
}
