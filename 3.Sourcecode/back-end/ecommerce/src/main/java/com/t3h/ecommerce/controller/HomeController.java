package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
//@Api("moduls shop api")
@RequestMapping("/api/")
public class HomeController {

    @Autowired
    private final ProductService service;


    @GetMapping("public/home")
    BaseResponse<?> getProductForHome(@Valid @RequestBody PageRequest pageRequest){
        return service.findProductForHome(pageRequest);
    }

}
