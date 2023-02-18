package com.t3h.ecommerce.pojo.dto.size;

import com.t3h.ecommerce.entities.product.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO {
    private Long id;
    private String sizeCode;
    private String sizeName;
    private Long createdDate;
    private Long updatedDate;

    public SizeDTO(Size size){
        BeanUtils.copyProperties(size, this);
    }
}
