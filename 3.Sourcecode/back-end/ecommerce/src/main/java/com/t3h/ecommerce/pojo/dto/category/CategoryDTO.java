package com.t3h.ecommerce.pojo.dto.category;


import com.t3h.ecommerce.entities.product.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String categoryName;
    private String description;
    private Long createdDate;
    private Long updatedDate;

    public CategoryDTO(Category category){
        BeanUtils.copyProperties(category,this);
    }
}
