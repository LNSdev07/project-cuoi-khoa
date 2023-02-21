package com.t3h.ecommerce.service;


import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.product.ProductDTO;

public interface ProductService {

         Boolean deleteProductByCatagory(String id);

         PageResponse<ProductDTO> findProduct(PageRequest pageRequest);
}
