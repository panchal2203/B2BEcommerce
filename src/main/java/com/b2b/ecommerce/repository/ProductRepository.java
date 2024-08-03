package com.b2b.ecommerce.repository;

import com.b2b.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingAndCityIdAndProductCategoryId(String searchString, Long cityId, Long productCategoryId);
    List<Product> findBySellerId(Long sellerId);
}
