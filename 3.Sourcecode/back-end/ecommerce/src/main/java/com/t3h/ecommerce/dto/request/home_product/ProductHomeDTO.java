package com.t3h.ecommerce.dto.request.home_product;

import com.t3h.ecommerce.entities.product.Product;
import lombok.Data;
import org.springframework.beans.BeanUtils;
@Data
public class ProductHomeDTO {
    private String productName;
    private String shortDescription;
    private Double cost;
    public ProductHomeDTO(Product product){
        BeanUtils.copyProperties(product,this);
    }
}
