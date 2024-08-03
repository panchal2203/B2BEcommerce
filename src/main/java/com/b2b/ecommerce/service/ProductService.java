package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.ProductDTO;
import com.b2b.ecommerce.dto.ReviewDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductDetails(Long productId);
    List<ProductDTO> searchProducts(String searchString, Long cityId, Long productCategoryId);
    List<ProductDTO> getProductsOfSeller(Long sellerId);
    String addProduct(Long sellerId, ProductDTO productDTO);
    String updateProduct(Long productId, ProductDTO productDTO);
    String removeProduct(Long productId);
    String addReview(Long productId, ReviewDTO reviewDTO);

}
