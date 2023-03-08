package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.discount.DiscountDTO;
import com.t3h.ecommerce.pojo.dto.discount.PageDiscountRequest;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
@CrossOrigin("http://localhost:4200")
public class DiscountController {

    @Autowired
    private DiscountService service;

    @GetMapping("/public/discounts")
    public BaseResponse<?> getAllDiscount(){
        return service.getAllDiscount();
    }


}
