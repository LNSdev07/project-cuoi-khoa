package com.t3h.ecommerce.service.Impl;


import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.entities.product.Category;
import com.t3h.ecommerce.pojo.dto.category.CategoryDTO;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.repositories.CategoryRepository;
import com.t3h.ecommerce.service.CategoryService;
import com.t3h.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


}
