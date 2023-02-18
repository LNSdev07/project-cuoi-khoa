package com.t3h.ecommerce.pojo.dto.color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageColorRequest {
    private Integer page;
    private Integer pageSize;

    private String textSearch;
}
