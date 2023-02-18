package com.t3h.ecommerce.service.Impl;

import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.entities.product.Color;
import com.t3h.ecommerce.pojo.dto.color.ColorDTO;
import com.t3h.ecommerce.pojo.dto.color.PageColorRequest;
import com.t3h.ecommerce.pojo.response.BaseResponse;
import com.t3h.ecommerce.repositories.ColorRepository;
import com.t3h.ecommerce.service.ColorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository repository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public PageResponse<ColorDTO> findColor(PageColorRequest pageColorRequest) {
        List<ColorDTO> list = new ArrayList<>();
        try{
            Pageable pageable = PageRequest.of(pageColorRequest.getPage(), pageColorRequest.getPageSize());

            Page<Color> page = repository.findColor(pageable, pageColorRequest.getTextSearch());

            list = page.getContent().stream().map(ColorDTO::new).collect(Collectors.toList());

            return new PageResponse<>(list, page.getTotalPages(), page.getTotalElements(), "success", 200);
        }catch (Exception e){
            return new PageResponse<>(list, 0, 0l, "fail", 309 );
        }
    }

    @Override
    public BaseResponse<ColorDTO> getColorById(String id){
        try {
            Color color = repository.findById(Long.parseLong(id.trim())).orElseThrow(
                    () -> new ClassNotFoundException("color not found!")
            );
            return new BaseResponse<>(200, "success", mapper.map(color, ColorDTO.class));
        }catch (Exception e){
            return new BaseResponse<>(309, "fail", new ColorDTO());
        }
    }

    @Override
    public BaseResponse<ColorDTO> addColor(ColorDTO colorDTO) {
        Color color;
        try{
            if(colorDTO.getId() ==0){
               color = new Color(colorDTO.getCreatedDate(), colorDTO.getUpdatedDate(),
                       colorDTO.getColorName(), colorDTO.getColorCode());
            }
            else{
                color = new Color(colorDTO.getCreatedDate(), colorDTO.getUpdatedDate(), colorDTO.getId(),
                        colorDTO.getColorName(), colorDTO.getColorCode());
            }

            repository.save(color);

            return new BaseResponse<>(200, "success", new ColorDTO());


        }catch (Exception e){
            return new BaseResponse<>(309, "fail", new ColorDTO());
        }
    }

    @Override
    public BaseResponse<ColorDTO> deteleById(String id) {
         try{
             repository.deleteById(Long.parseLong(id.trim()));
             return new BaseResponse<>(200, "success", new ColorDTO());
         }catch (Exception e){
             return new BaseResponse<>(309, "fail", new ColorDTO());
         }
    }
}
