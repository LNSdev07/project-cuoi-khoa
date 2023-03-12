package com.t3h.ecommerce.controller;

import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.category.CategoryDTO;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
@CrossOrigin("http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService service;


    @GetMapping("/public/categories")
    public BaseResponse<?> getAllColor(){
        return service.getAllCategory();
    }
}
