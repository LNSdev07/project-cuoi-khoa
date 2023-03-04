package com.t3h.ecommerce.service.Impl;

import com.t3h.ecommerce.dto.request.admin_product.ProductAdminDTO;
import com.t3h.ecommerce.dto.request.admin_product.ProductAdminRequest;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.entities.product.Product;
import com.t3h.ecommerce.pojo.dto.product.ProductDTO;
import com.t3h.ecommerce.repositories.ProductRepository;
import com.t3h.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository repository;


    @Override
    public BaseResponse<?> findProduct(ProductAdminRequest request) {
        if(request == null) return  BaseResponse.builder().message("request not found!").status(HttpStatus.BAD_REQUEST.value()).build();

        try{


            Pageable pageable = PageRequest.of(request.getPageRequest().getPage()-1, request.getPageRequest().getPageSize(),
                    Sort.Direction.fromString(request.getPageRequest().getCondition().equals("asc")? "asc": "desc"),
                    (request.getPageRequest().getSortBy().isEmpty() || request.getPageRequest().getSortBy().equals("createdDate"))?
                    "createdDate": request.getPageRequest().getSortBy());

            Page<Product> page = repository.findProduct(pageable,
                    request.getProductName(), request.getQuantity(), request.getCost(), request.getCategoryId(),
                    request.getFilterDate().getCreatedDateStart(), request.getFilterDate().getCreatedDateEnd(),
                    request.getFilterDate().getUpdatedDateStart(), request.getFilterDate().getUpdatedDateEnd());

            if(page == null) return  BaseResponse.builder().message("request not found!").status(HttpStatus.BAD_REQUEST.value()).build();

            List<ProductAdminDTO>  list = page.getContent().stream().map(ProductAdminDTO::new).collect(Collectors.toList());

            return BaseResponse.builder().data(list).message("success").status(HttpStatus.OK.value()).totalRecords(page.getTotalElements()).build();

        }catch (Exception ex){
            log.error("can not call repository in product service");
            return BaseResponse.builder().message("request bad").status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public BaseResponse<?> deleteProduct(String Ids) {
        if(Ids == null || Ids.isEmpty()) return BaseResponse.builder().message("delete fail").status(HttpStatus.BAD_REQUEST.value()).build();

        try{
            String[] arr = Ids.split(",");
            List<Long> ids = new ArrayList<>();
            for(int i=0 ;i< arr.length; i++){
                ids.add(Long.parseLong(arr[i]));
            }
            repository.deleteProduct(ids);
            return BaseResponse.builder().message("delete success").status(HttpStatus.OK.value()).build();
        } catch (Exception ex){
            return BaseResponse.builder().message("delete fail").status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}
