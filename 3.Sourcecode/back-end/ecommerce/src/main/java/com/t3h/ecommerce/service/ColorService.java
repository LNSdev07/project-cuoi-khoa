package com.t3h.ecommerce.service;

import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.color.ColorDTO;
import com.t3h.ecommerce.pojo.dto.color.PageColorRequest;
import com.t3h.ecommerce.pojo.response.BaseResponse;

public interface ColorService {

    PageResponse<ColorDTO> findColor(PageColorRequest pageColorRequest);

    BaseResponse<ColorDTO> getColorById(String id);

    BaseResponse<ColorDTO> addColor(ColorDTO colorDTO);

    BaseResponse<ColorDTO> deteleById(String id);
}
