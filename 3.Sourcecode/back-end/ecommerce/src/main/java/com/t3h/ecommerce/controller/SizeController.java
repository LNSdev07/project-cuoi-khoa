package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.request.PageRequest;
import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.size.PageSizeRequest;
import com.t3h.ecommerce.pojo.dto.size.SizeDTO;
import com.t3h.ecommerce.pojo.response.BaseResponse;
import com.t3h.ecommerce.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
@CrossOrigin("http://localhost:4200")
public class SizeController {

    @Autowired
    private SizeService service;

    @PostMapping("sizes")
    public PageResponse<SizeDTO> findSize(@RequestBody @Valid PageSizeRequest pageRequest){
        return service.findSize(pageRequest);
    }

    @PostMapping("size")
    public BaseResponse<SizeDTO> addSize(@RequestBody @Valid SizeDTO sizeDTO){
        return service.addSize(sizeDTO);
    }

    @DeleteMapping("size")
    public BaseResponse<SizeDTO> deleteById(@Valid @RequestParam("id") String id){
        return service.deleteById(id);
    }

    @GetMapping("size")
    public BaseResponse<SizeDTO> getSizeById(@Valid @RequestParam("id") String id){
        return service.getSizeById(id);
    }

    @PutMapping("size")
    public BaseResponse<SizeDTO> updateSize(@RequestBody @Valid SizeDTO sizeDTO){
        return service.updateSize(sizeDTO);
    }
}
