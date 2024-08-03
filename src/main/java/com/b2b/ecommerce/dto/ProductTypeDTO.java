package com.b2b.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeDTO {
    private long id;
    private String description;
    private String code;
    private ProductCategoryDTO productCategory;
    private String measuringUnit;
}
