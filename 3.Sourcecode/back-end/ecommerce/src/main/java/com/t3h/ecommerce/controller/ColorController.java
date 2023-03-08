package com.t3h.ecommerce.controller;


import com.t3h.ecommerce.dto.response.BaseResponse;
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

    @GetMapping("/public/colors")
    public BaseResponse<?> getAllColor(){
        return service.getAllColor();
    }


}
