package com.t3h.ecommerce.pojo.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T>{
   // http code
    private int httpCode;

    private String message;

    private T data;

}
