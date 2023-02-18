package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.pojo.dto.color.ColorDTO;
import com.t3h.ecommerce.pojo.dto.color.PageColorRequest;
import com.t3h.ecommerce.pojo.response.BaseResponse;
import com.t3h.ecommerce.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
@CrossOrigin("http://localhost:4200")
public class ColorController {


    @Autowired
    private ColorService service;


    @PostMapping("colors")
    public PageResponse<ColorDTO> findColor(@RequestBody @Valid PageColorRequest pageColorRequest){
        return service.findColor(pageColorRequest);
    }

    @GetMapping("color")
    public BaseResponse<ColorDTO> getColorById(@Valid @RequestParam("id") String id){
        return service.getColorById(id);
    }


    @PostMapping("color")
    public BaseResponse<ColorDTO> addColor(@Valid @RequestBody ColorDTO colorDTO){
        return service.addColor(colorDTO);
    }

    @DeleteMapping("color")
    public BaseResponse<ColorDTO> deleteColorById(@Valid @RequestParam("id") String id){
        return service.deteleById(id);
    }


}
