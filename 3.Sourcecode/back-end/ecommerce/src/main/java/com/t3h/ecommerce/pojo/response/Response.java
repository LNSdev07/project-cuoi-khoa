package com.t3h.ecommerce.pojo.response;

import lombok.Data;

import java.util.Collections;
import java.util.List;


@Data
public class Response<T> {
    private List<T> data = Collections.emptyList();
    private Long total;
    private Integer totalPage;
}
