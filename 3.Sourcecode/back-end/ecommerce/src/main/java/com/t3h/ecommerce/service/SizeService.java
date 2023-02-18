package com.t3h.ecommerce.service;


import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.size.PageSizeRequest;
import com.t3h.ecommerce.pojo.dto.size.SizeDTO;
import com.t3h.ecommerce.pojo.response.BaseResponse;

public interface SizeService {

    PageResponse<SizeDTO> findSize(PageSizeRequest pageSizeRequest);

    BaseResponse<SizeDTO> addSize(SizeDTO sizeDTO);

    BaseResponse<SizeDTO>deleteById(String id);

    BaseResponse<SizeDTO> getSizeById(String id);

    BaseResponse<SizeDTO> updateSize(SizeDTO sizeDTO);
}
