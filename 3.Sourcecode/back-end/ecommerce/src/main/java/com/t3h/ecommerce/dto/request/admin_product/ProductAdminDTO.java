package com.t3h.ecommerce.dto.request.admin_product;

import com.t3h.ecommerce.entities.product.Category;
import com.t3h.ecommerce.entities.product.Product;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ProductAdminDTO {
    private String productName;
    private String shortDescription;
    private Double cost;
    private Long quantity;
    private Long category;
    private Long createdDate;
    private Long updatedDate;

    public ProductAdminDTO(Product product){
        BeanUtils.copyProperties(product,this);
    }
}
