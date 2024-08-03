package com.b2b.ecommerce.controller;


import com.b2b.ecommerce.dto.ProductCategoryDTO;
import com.b2b.ecommerce.service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping
    public ResponseEntity<String> addProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        boolean isSuccess = productCategoryService.addProductCategory(productCategoryDTO);
        if (isSuccess) {
            return ResponseEntity.ok("Product category added successfully!");
        } else {
            return ResponseEntity.status(500).body("Error adding product category.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProductCategory(@PathVariable Long id) {
        boolean isSuccess = productCategoryService.removeProductCategory(id);
        if (isSuccess) {
            return ResponseEntity.ok("Product category removed successfully!");
        } else {
            return ResponseEntity.status(500).body("Error removing product category.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductCategory(@PathVariable Long id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        boolean isSuccess = productCategoryService.updateProductCategory(id, productCategoryDTO);
        if (isSuccess) {
            return ResponseEntity.ok("Product category updated successfully!");
        } else {
            return ResponseEntity.status(500).body("Error updating product category.");
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategories() {
        List<ProductCategoryDTO> categories = productCategoryService.getAllProductCategories();
        return ResponseEntity.ok(categories);
    }
}
