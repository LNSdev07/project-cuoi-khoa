package com.t3h.ecommerce.service.Impl;


import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.dto.response.ResponseMessage;
import com.t3h.ecommerce.entities.product.Category;
import com.t3h.ecommerce.pojo.dto.category.CategoryDTO;
import com.t3h.ecommerce.pojo.response.BaseResponse;
import com.t3h.ecommerce.repositories.CategoryRepository;
import com.t3h.ecommerce.service.CategoryService;
import com.t3h.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductService productService;

    @Override
    public PageResponse<CategoryDTO> findCategory(PageRequest pageRequest) {

        List<CategoryDTO> list = new ArrayList<>();
        try {
            Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getPageSize());
            list = new ArrayList<>();
            Integer searchIsEmpty =
                    (pageRequest.getTextSearch().isEmpty() || pageRequest.getTextSearch().isBlank()) ? 1 : 0;
            Page<Category> page = repository.findCategory(pageable, searchIsEmpty, pageRequest.getTextSearch());

            if (page.getContent().isEmpty() || page.getContent() == null) {
                return new PageResponse<>(list, 0,0l, "fail", 309);
            } else {
                list = page.getContent().stream().map(CategoryDTO::new).collect(Collectors.toList());

                return new PageResponse<>(list, page.getTotalPages(),page.getTotalElements(),  "success", 200);
            }
        } catch (Exception ex) {
            return new PageResponse<>(list, 0,0l, "fail", 309);
        }
    }

    @Override
    public PageResponse<CategoryDTO> addCategory(CategoryDTO categoryDTO) {
        List<CategoryDTO> list = new ArrayList<>();
        Category category = new Category();
        if(categoryDTO.getId() == 0){
             category = new Category(categoryDTO.getCreatedDate(), categoryDTO.getUpdatedDate(),
                     categoryDTO.getCategoryName(), categoryDTO.getDescription());
        }
        else{
            category = mapper.map(categoryDTO, Category.class);
        }

        if(category != null){
             repository.save(category);
             return findCategory(new PageRequest("", 0, 6));
        }
        else{
            return new PageResponse<>(list, 0,0l, "fail", 309);
        }
    }

    @Override
    public BaseResponse<CategoryDTO> getCategoryById(String id) {
        CategoryDTO categoryDTO = new CategoryDTO();
        try{
            Category category;
            category = repository.findById(Long.parseLong(id.trim())).orElseThrow(
                    () -> new ClassNotFoundException("category not found!")
            );

            categoryDTO = mapper.map(category, CategoryDTO.class);

            return new BaseResponse<>(200, "success", categoryDTO );

        } catch (Exception ex){
            return new BaseResponse<>(404, "categoty not found!!", categoryDTO);
        }
    }

    @Override
    public BaseResponse<CategoryDTO> deleteCategoryById(String id) {
        try{
            repository.deleteById(Long.parseLong(id.trim())); // xoa category id
            productService.deleteProductByCatagory(id); // xoa product by category id

            return new BaseResponse<>(200, "success", new CategoryDTO());
        }catch (Exception exception){
            return new BaseResponse<>(309, "fail", new CategoryDTO());
        }
    }
}
