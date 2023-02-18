package com.t3h.ecommerce.pojo.resquest;

import lombok.Data;

@Data
public class TableRequest<T> {
   private Integer pageIndex;
   private Integer pageSize;
   private T filterRequest;
}
