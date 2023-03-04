package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.request.ProductDetail.ProductHomeRequest;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
//@Api("moduls shop api")
@RequestMapping("/api/")
public class HomeController {

    @Autowired
    private final ProductService service;

//    @GetMapping("public/home")
//    BaseResponse<?> getProductForHome(ProductHomeRequest request){
//        return service.findProductForHome(request);
//    }

    @GetMapping("public/home")
    BaseResponse<?> getProductForHome( ){
        return service.findProductForHome( );
    }

}
