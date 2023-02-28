package com.t3h.ecommerce.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T>{
   // http code
    private int httpCode;

    private String message;

    private T data;

}
