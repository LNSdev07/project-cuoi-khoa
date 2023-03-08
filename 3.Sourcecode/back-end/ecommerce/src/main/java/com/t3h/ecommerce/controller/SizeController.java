package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.size.PageSizeRequest;
import com.t3h.ecommerce.pojo.dto.size.SizeDTO;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
@CrossOrigin("http://localhost:4200")
public class SizeController {

    @Autowired
    private SizeService service;

    @GetMapping("/public/sizes")
    public BaseResponse<?> getAllSize(){
        return service.getAllSize();
    }
}
