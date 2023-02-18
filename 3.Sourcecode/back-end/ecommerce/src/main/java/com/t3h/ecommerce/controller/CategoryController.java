package com.t3h.ecommerce.controller;

import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.entities.product.Category;
import com.t3h.ecommerce.pojo.dto.category.CategoryDTO;
import com.t3h.ecommerce.pojo.response.BaseResponse;
import com.t3h.ecommerce.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/")
@CrossOrigin("http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService service;


    @PostMapping("categories")
    public PageResponse<CategoryDTO> findCategory( @Valid @RequestBody PageRequest pageRequest){
        return service.findCategory(pageRequest);
    }

    @PostMapping("category")
    public PageResponse<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return service.addCategory(categoryDTO);
    }

    @GetMapping("category")
    public BaseResponse<CategoryDTO> getCategoryById(@RequestParam("id") @Valid String id){
        return service.getCategoryById(id);
    }


    @DeleteMapping("category")
    public BaseResponse<CategoryDTO> deleteById(@RequestParam("id") @Valid String id){
        return service.deleteCategoryById(id);
    }
}
