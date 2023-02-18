package com.t3h.ecommerce.service;

import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.discount.DiscountDTO;
import com.t3h.ecommerce.pojo.dto.discount.PageDiscountRequest;
import com.t3h.ecommerce.pojo.response.BaseResponse;

public interface DiscountService {


    PageResponse<DiscountDTO> findDiscount(PageDiscountRequest request);

    BaseResponse<DiscountDTO> addDiscount(DiscountDTO discountDTO);

    BaseResponse<DiscountDTO> getDiscountById(String id);

    BaseResponse<DiscountDTO> deleteById(String id);

}
