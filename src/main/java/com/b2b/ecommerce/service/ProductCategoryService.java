package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    boolean addProductCategory(ProductCategoryDTO productCategoryDTO);
    boolean removeProductCategory(Long id);
    boolean updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO);
    List<ProductCategoryDTO> getAllProductCategories();
}
