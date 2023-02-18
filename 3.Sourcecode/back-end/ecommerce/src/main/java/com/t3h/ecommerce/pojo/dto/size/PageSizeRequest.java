package com.t3h.ecommerce.pojo.dto.size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageSizeRequest {
    private String textSearch;
    private Integer page;
    private Integer pageSize;
}
