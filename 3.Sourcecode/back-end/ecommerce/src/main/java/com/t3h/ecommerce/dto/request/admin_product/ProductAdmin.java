package com.t3h.ecommerce.dto.request.admin_product;

public interface ProductAdmin {
     Long getId();
     String getProductName();
     String getShortDescription();
     Double getCost();
     Long getQuantity();
     Long getCategoryId();
     Long getCreatedDate();
     Long getUpdatedDate();
}
