package com.t3h.ecommerce.dto.request.shop_product;

import com.t3h.ecommerce.entities.product.Product;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ShopProductDTO {

    private String productName;
    private Double cost;
    private String imgAvt;
    public ShopProductDTO(Product product){
        BeanUtils.copyProperties(product,this);
    }

}
