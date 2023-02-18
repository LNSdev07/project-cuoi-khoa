package com.t3h.ecommerce.pojo.dto.discount;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDiscountRequest {
    private Integer page;
    private Integer pageSize;

    private String textSearch ="";

    private Float minDis;
    private Float maxDis;
}
