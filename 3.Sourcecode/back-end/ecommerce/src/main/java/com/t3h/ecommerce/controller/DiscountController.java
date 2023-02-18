package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.discount.DiscountDTO;
import com.t3h.ecommerce.pojo.dto.discount.PageDiscountRequest;
import com.t3h.ecommerce.pojo.response.BaseResponse;
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


    @PostMapping("discounts")
    public PageResponse<DiscountDTO> findDiscount(@Valid @RequestBody PageDiscountRequest pageDiscountRequest){
        return service.findDiscount(pageDiscountRequest);
    }

    @PostMapping("discount")
    public BaseResponse<DiscountDTO> addDisCount(@Valid @RequestBody DiscountDTO discountDTO){
        return service.addDiscount(discountDTO);
    }


    @GetMapping("discount")
    public BaseResponse<DiscountDTO> getDiscount(@Valid @RequestParam("id") String id){
        return service.getDiscountById(id);
    }

    @DeleteMapping("discount")
    public BaseResponse<DiscountDTO> deleteById(@Valid @RequestParam("id") String id){
        return service.deleteById(id);
    }

}
