package com.t3h.ecommerce.service.Impl;


import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.entities.product.Category;
import com.t3h.ecommerce.entities.product.Color;
import com.t3h.ecommerce.pojo.dto.category.CategoryDTO;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.pojo.dto.color.ColorDTO;
import com.t3h.ecommerce.repositories.CategoryRepository;
import com.t3h.ecommerce.service.CategoryService;
import com.t3h.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private final CategoryRepository repository;

    @Override
    public BaseResponse<?> getAllCategory() {
        try{
            Iterable<Category> iterable= repository.findAll();
            List<Category> list = new ArrayList<>();
            iterable.forEach(x -> list.add(x));
            List<CategoryDTO> result = list.stream().map(CategoryDTO::new).collect(Collectors.toList());

            return BaseResponse.builder().data(result).message("request success").status(HttpStatus.OK.value()).build();

        }catch (Exception ex){
            return BaseResponse.builder().message("delete fail").status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}
