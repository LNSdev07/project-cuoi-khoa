package com.t3h.ecommerce.service.Impl;

import com.t3h.ecommerce.dto.request.admin_customer.CustomerAdmin;
import com.t3h.ecommerce.dto.request.admin_customer.CustomerAdminRequest;
import com.t3h.ecommerce.dto.request.admin_customer.CustomerAdminResponse;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.entities.core.RoleName;
import com.t3h.ecommerce.entities.core.User;
import com.t3h.ecommerce.repositories.CustomerRepository;
import com.t3h.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;


    @Override
    public BaseResponse<?> findCustomer(CustomerAdminRequest request) {
        try{
            Pageable pageable = PageRequest.of(request.getPageRequest().getPage()-1, request.getPageRequest().getPageSize(),
                    Sort.Direction.fromString(request.getPageRequest().getCondition().equals("asc")?"asc":"desc"),
                    (request.getPageRequest().getSortBy().isEmpty() || request.getPageRequest().getSortBy().equals("createdDate"))?
                            "createdDate": request.getPageRequest().getSortBy());

            Page<CustomerAdmin> page = repository.findCustomer(pageable, RoleName.USER,
                    request.getPhoneNumber(), request.getStatus(), request.getTextSearch());

            if(page == null){
                return BaseResponse.builder().message("request fail").status(HttpStatus.BAD_REQUEST.value()).build();
            }
            List<CustomerAdminResponse> list = page.getContent().stream().map(CustomerAdminResponse::new).collect(Collectors.toList());

            return BaseResponse.builder().data(list).message("request success")
                    .status(HttpStatus.OK.value()).totalRecords(page.getTotalElements()).build();

        }catch (Exception e){
            return BaseResponse.builder().message("request fail").status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public BaseResponse<?> changeStatus(String ids, String status) {
        List<Long> idss = new ArrayList<>();
        Integer statuss = null;
        if(ids != null && !ids.isEmpty() && status != null && !status.isEmpty()){
            String[] arr = ids.trim().split(",");
            for(int i=0; i < arr.length; i++){
                idss.add(Long.parseLong(arr[i]));
            }
            statuss = Integer.parseInt(status.trim());
        }
        try{
            if(status != null && idss != null && !idss.isEmpty()){
                repository.changeStatus(statuss, idss, new Date().getTime());
                return BaseResponse.builder().message("success").status(HttpStatus.OK.value()).build();
            }
            else{
                return BaseResponse.builder().message("request fail").status(HttpStatus.BAD_REQUEST.value()).build();
            }
        }catch (Exception e){
            return BaseResponse.builder().message("request fail").status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}
