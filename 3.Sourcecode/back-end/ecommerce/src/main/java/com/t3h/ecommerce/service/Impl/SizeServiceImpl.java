package com.t3h.ecommerce.service.Impl;


import com.t3h.ecommerce.dto.response.PageResponse;
import com.t3h.ecommerce.entities.product.Size;
import com.t3h.ecommerce.pojo.dto.size.PageSizeRequest;
import com.t3h.ecommerce.pojo.dto.size.SizeDTO;
import com.t3h.ecommerce.pojo.response.BaseResponse;
import com.t3h.ecommerce.repositories.SizeRepository;
import com.t3h.ecommerce.service.SizeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository repository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public PageResponse<SizeDTO> findSize(PageSizeRequest pageSizeRequest) {
        List<SizeDTO> list = new ArrayList<>();
        try{
            Pageable pageable = PageRequest.of(pageSizeRequest.getPage(), pageSizeRequest.getPageSize());

            Page<Size> page = repository.findSize(pageable, pageSizeRequest.getTextSearch());
            list = page.getContent().stream().map(SizeDTO::new).collect(Collectors.toList());

            return new PageResponse<>(list, page.getTotalPages(), page.getTotalElements(), "success", 200 );
        }catch (Exception ex){
            return new PageResponse<>(list, 0,0l, "failed", 309);
        }
    }

    @Override
    public BaseResponse<SizeDTO> addSize(SizeDTO sizeDTO) {
        try{
            if(sizeDTO.getId() == 0){
                Size size = new Size(sizeDTO.getCreatedDate(),sizeDTO.getUpdatedDate(),
                        sizeDTO.getSizeCode(), sizeDTO.getSizeName());

                repository.save(size);
            }
            else{
                Size size = new Size(sizeDTO.getCreatedDate(),sizeDTO.getUpdatedDate(),
                     sizeDTO.getId(),sizeDTO.getSizeCode(), sizeDTO.getSizeName());

                repository.save(size);
            }
            return new BaseResponse<>(200, "success", new SizeDTO());
        } catch (Exception e){
            return new BaseResponse<>(309, "fail", new SizeDTO() );
        }
    }

    @Override
    public BaseResponse<SizeDTO> deleteById(String id) {
        try{
            repository.deleteById(Long.parseLong(id.trim()));

            return new BaseResponse<>(200, "success", new SizeDTO());
        }catch (Exception e){
            return new BaseResponse<>(309, "fail", new SizeDTO() );

        }
    }

    @Override
    public BaseResponse<SizeDTO> getSizeById(String id) {
        try{
            Size size = repository.getById(Long.parseLong(id.trim()));
             SizeDTO sizeDTO = mapper.map(size, SizeDTO.class);

            return new BaseResponse<>(200, "success", sizeDTO );
        }catch (Exception e){
            return new BaseResponse<>(309, "fail", new SizeDTO() );
        }
    }

    @Override
    public BaseResponse<SizeDTO> updateSize(SizeDTO sizeDTO) {
        try{
            Size size = new Size(sizeDTO.getCreatedDate(), sizeDTO.getUpdatedDate(),
                    sizeDTO.getId(), sizeDTO.getSizeCode(), sizeDTO.getSizeName());

             repository.save(size);
           return  new BaseResponse<>(200, "success", new SizeDTO() );
        }catch (Exception e){
            return new BaseResponse<>(309, "fail", new SizeDTO() );
        }
    }


}
