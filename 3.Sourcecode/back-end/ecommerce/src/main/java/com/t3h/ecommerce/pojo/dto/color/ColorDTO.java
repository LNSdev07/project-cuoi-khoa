package com.t3h.ecommerce.pojo.dto.color;

import com.t3h.ecommerce.entities.product.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorDTO {
    private Long id;
    private String colorName;
    private String colorCode;
    private Long createdDate;
    private Long updatedDate;

    public ColorDTO(Color color){
        BeanUtils.copyProperties(color, this);
    }
}
