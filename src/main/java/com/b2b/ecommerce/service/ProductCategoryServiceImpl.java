package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.ProductCategoryDTO;
import com.b2b.ecommerce.entity.ProductCategory;
import com.b2b.ecommerce.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public boolean addProductCategory(ProductCategoryDTO productCategoryDTO) {
        try {
            ProductCategory category = new ProductCategory();
            category.setName(productCategoryDTO.getName());
            productCategoryRepository.save(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeProductCategory(Long id) {
        try {
            productCategoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO) {
        try {
            ProductCategory category = productCategoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product category not found"));

            category.setName(productCategoryDTO.getName());
            productCategoryRepository.save(category);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ProductCategoryDTO> getAllProductCategories() {
        return productCategoryRepository.findAll().stream()
                .map(category -> {
                    ProductCategoryDTO dto = new ProductCategoryDTO();
                    dto.setId(category.getId());
                    dto.setName(category.getName());
                    return dto;
                }).collect(Collectors.toList());
    }
}
