package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.product.ProductDTO;
import com.t3h.ecommerce.pojo.response.TableRespone;
import com.t3h.ecommerce.pojo.resquest.TableFilter;
import com.t3h.ecommerce.pojo.resquest.TableRequest;
import com.t3h.ecommerce.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@Api("moduls shop api")
public class ProductController {

    private final ProductService service;

    @ApiOperation(value = "get list product with filter")
    @GetMapping(value = "api/products")
    public String findProduct(@RequestParam(name = "sorts", required = false, defaultValue = "3") String sorts,
                              @RequestParam(name = "colors", required = false, defaultValue = "") String colors,
                              @RequestParam(name = "sizes", required = false, defaultValue = "") String sizes,
                              @RequestParam(name = "page", required = false, defaultValue = "1") String page ){
        return service.findProductWithFilter(sorts, colors, sizes, page);

    }


    @PostMapping("api/products")
    public PageResponse<ProductDTO> findProduct(@RequestBody @Valid PageRequest pageRequest){
        return  service.findProduct(pageRequest);
    }

}
