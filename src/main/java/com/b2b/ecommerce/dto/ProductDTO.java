package com.b2b.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double mrp;
    private Double minPrice;
    private Long sellerId;
    private String sellerName;
    private String sellerCityName;
    private Long cityId;
    private Long productCategoryId;
}
