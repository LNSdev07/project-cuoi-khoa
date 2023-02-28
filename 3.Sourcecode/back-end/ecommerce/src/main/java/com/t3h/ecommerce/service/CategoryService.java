package com.t3h.ecommerce.service;

import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.category.CategoryDTO;
import com.t3h.ecommerce.dto.response.BaseResponse;

public interface CategoryService {

    PageResponse<CategoryDTO> findCategory(PageRequest pageRequest);

    PageResponse<CategoryDTO> addCategory(CategoryDTO categoryDTO);

    BaseResponse<CategoryDTO> getCategoryById(String id);

    BaseResponse<CategoryDTO> deleteCategoryById(String id);
}
