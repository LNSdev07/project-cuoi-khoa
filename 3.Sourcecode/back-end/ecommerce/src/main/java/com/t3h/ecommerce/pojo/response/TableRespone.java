package com.t3h.ecommerce.pojo.response;

import com.t3h.ecommerce.pojo.dto.product.ProductDTO;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collections;
import java.util.List;


@Data
public class TableRespone<T> extends BaseResponse<Response<ProductDTO>>{
}
