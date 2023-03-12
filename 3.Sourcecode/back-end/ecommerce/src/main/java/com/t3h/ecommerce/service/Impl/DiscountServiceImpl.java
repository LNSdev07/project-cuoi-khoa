package com.t3h.ecommerce.service.Impl;

import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.entities.product.Discount;
import com.t3h.ecommerce.pojo.dto.discount.DiscountDTO;
import com.t3h.ecommerce.pojo.dto.discount.PageDiscountRequest;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.repositories.DiscountRepository;
import com.t3h.ecommerce.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {


    @Autowired
     private final DiscountRepository repository;

    @Autowired
     private ModelMapper mapper;

    List<DiscountDTO> list = new ArrayList<>();
    @Override
    public PageResponse<DiscountDTO> findDiscount(PageDiscountRequest request) {
        try{
            Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize());

            Page<Discount> page = repository.findDiscount(pageable,
                    request.getTextSearch(), request.getMinDis(), request.getMaxDis());

            list = page.getContent().stream().map(DiscountDTO::new).collect(Collectors.toList());
            return new PageResponse<>(list, page.getTotalPages(), page.getTotalElements(), "success", 200);

        }catch (Exception e){
            return new PageResponse<>(list, 0, 0l, "fail", 309 );
        }
    }

    @Override
    public BaseResponse<DiscountDTO> addDiscount(DiscountDTO discountDTO) {

        try{
            Discount discount;
            if(discountDTO.getId() ==0){
                discount = new Discount(discountDTO.getCreatedDate(), discountDTO.getUpdatedDate(),
                        discountDTO.getDiscountName(), discountDTO.getDiscountPercent());
            }
            else{
                discount = new Discount(discountDTO.getCreatedDate(), discountDTO.getUpdatedDate(), discountDTO.getId(),
                        discountDTO.getDiscountName(), discountDTO.getDiscountPercent());
            }
            repository.save(discount);
            return new BaseResponse<>(200,0l, "success", new DiscountDTO());
        }catch (Exception e){
            return new BaseResponse<>(309,0l, "fail", new DiscountDTO());
        }
    }

    @Override
    public BaseResponse<DiscountDTO> getDiscountById(String id) {
        try {
            Discount discount = repository.findById(Long.parseLong(id.trim())).orElseThrow(
                    () -> new ClassNotFoundException("discount not found!")
            );
            return new BaseResponse<>(200,0l, "success", mapper.map(discount, DiscountDTO.class));
        }catch (Exception e){
            return new BaseResponse<>(309,0l, "fail", new DiscountDTO());
        }
    }

    @Override
    public BaseResponse<DiscountDTO> deleteById(String id) {
        try{
            repository.deleteById(Long.parseLong(id.trim()));

            return new BaseResponse<>(200,0l, "success", new DiscountDTO());
        }catch (Exception e){
            return new BaseResponse<>(309,0l, "fail", new DiscountDTO());
        }
    }

    @Override
    public BaseResponse<?> getAllDiscount() {
        try{
            Iterable<Discount> iterable= repository.findAll();
            List<Discount> list = new ArrayList<>();
            iterable.forEach(x -> list.add(x));
            List<DiscountDTO> result = list.stream().map(DiscountDTO::new).collect(Collectors.toList());

            return BaseResponse.builder().data(result).message("request success").status(HttpStatus.OK.value()).build();

        }catch (Exception ex){
            return BaseResponse.builder().message("delete fail").status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}
