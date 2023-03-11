package com.t3h.ecommerce.service;


import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.request.admin_product.ProductAdminAddRequest;
import com.t3h.ecommerce.dto.request.admin_product.ProductAdminDTO;
import com.t3h.ecommerce.dto.request.admin_product.ProductAdminRequest;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.pojo.dto.product.ProductDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;


public interface ProductService {

    BaseResponse<?> findProduct(ProductAdminRequest request);

    BaseResponse<?> deleteProduct(String Ids);

    BaseResponse<?> createOrEditProduct(ProductAdminAddRequest request);

    BaseResponse<?> findProductById(String id);

    BaseResponse<?> findProductForHome(PageRequest pageRequest);

    ResponseEntity<Resource> exportExcelProduct();

}
