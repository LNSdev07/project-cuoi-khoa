package com.t3h.ecommerce.dto.request.ProductDetail;

import lombok.Data;

@Data
public class ProductHomeRequest {
    private Long id;
    private String productName;
    private Double cost;
    private String imgAvt;
}
