package com.t3h.ecommerce.pojo.dto.discount;


import com.t3h.ecommerce.entities.product.Discount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO {
    private Long id;
    private Long createdDate;
    private Long updatedDate;
    private String discountName;
    private Float discountPercent;

    public DiscountDTO(Discount discount){
        BeanUtils.copyProperties(discount, this);
    }
}
