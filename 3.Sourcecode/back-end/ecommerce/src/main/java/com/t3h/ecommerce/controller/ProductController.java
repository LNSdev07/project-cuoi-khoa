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
@RequestMapping("/api/admin/")
public class ProductController {


    @Autowired
    private final ProductService service;


    @PostMapping("product")
    BaseResponse<?> findProduct(@Valid @RequestBody ProductAdminRequest request){
        System.out.println("vao day");
        return service.findProduct(request);
    }
}
