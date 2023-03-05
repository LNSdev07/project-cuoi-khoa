package com.t3h.ecommerce.service;


import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.request.admin_product.ProductAdminRequest;
import com.t3h.ecommerce.dto.response.BaseResponse;


public interface ProductService {

    BaseResponse<?> findProduct(ProductAdminRequest request);

    BaseResponse<?> findProductForHome(PageRequest pageRequest);

}
