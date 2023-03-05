package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.request.admin_product.ProductAdminRequest;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@Api("moduls shop api")
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/")
public class ProductController {


    @Autowired
    private final ProductService service;


    @PostMapping("admin/product")
    BaseResponse<?> findProduct(@Valid @RequestBody ProductAdminRequest request){
        return service.findProduct(request);
    }


    @DeleteMapping("admin/product")
    BaseResponse<?> deleteProduct( @Valid @RequestParam("Ids") String Ids){
        return service.deleteProduct(Ids);
    }
}
