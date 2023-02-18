package com.t3h.ecommerce.dto.request;

import com.t3h.ecommerce.pojo.dto.product.CostRequestPage;
import com.t3h.ecommerce.pojo.dto.product.QuantityRequestPage;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private String textSearch;
    private Integer page;
    private Integer pageSize;

    private CostRequestPage costRequestPage;

    private QuantityRequestPage quantityRequestPage;


    public PageRequest(String textSearch, Integer page, Integer pageSize){
        this.textSearch = textSearch;
        this.page = page;
        this.pageSize =pageSize;
    }
}
