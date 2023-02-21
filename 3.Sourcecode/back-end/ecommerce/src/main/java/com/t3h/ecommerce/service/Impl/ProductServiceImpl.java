package com.t3h.ecommerce.service.Impl;

import com.google.gson.Gson;
import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.entities.product.Product;
import com.t3h.ecommerce.pojo.dto.product.ProductDB;
import com.t3h.ecommerce.pojo.dto.product.ProductDTO;
import com.t3h.ecommerce.pojo.response.Response;
import com.t3h.ecommerce.pojo.response.TableRespone;
import com.t3h.ecommerce.pojo.resquest.TableFilter;
import com.t3h.ecommerce.pojo.resquest.TableRequest;
import com.t3h.ecommerce.repositories.ProductRepository;
import com.t3h.ecommerce.service.ProductService;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Boolean deleteProductByCatagory(String id) {
            try{
                repository.deleteProductByCategoryId(Long.parseLong(id.trim()));
                return true;
            }catch (Exception ex){
                return false;
            }
    }

    @Override
    public PageResponse<ProductDTO> findProduct(com.t3h.ecommerce.dto.request.PageRequest pageRequest) {
        List<ProductDTO> list = new ArrayList<>();
        try{

            Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getPageSize());
            Page<Product> page = repository.findProduct(pageable, pageRequest.getTextSearch(), pageRequest.getCostRequestPage().getMinCost(),
                    pageRequest.getCostRequestPage().getMaxCost(), pageRequest.getQuantityRequestPage().getMinQuantity(),
                    pageRequest.getQuantityRequestPage().getMaxQuantity());
            list = page.getContent().stream().map(ProductDTO::new).collect(Collectors.toList());

            return new PageResponse<>(list, page.getTotalPages(), page.getTotalElements(), "success", 200);

        }catch (Exception e){
            return new PageResponse<>(list, 0,0l, "failed", 309);
        }
    }
}
