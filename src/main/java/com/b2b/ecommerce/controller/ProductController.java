package com.b2b.ecommerce.controller;

// ProductController.java
import com.b2b.ecommerce.dto.ProductCategoryDTO;
import com.b2b.ecommerce.dto.ProductDTO;
import com.b2b.ecommerce.dto.ReviewDTO;
import com.b2b.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get product details
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductDetails(@PathVariable Long productId) {
        ProductDTO product = productService.getProductDetails(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Search products
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(
            @RequestParam(required = false) String searchString,
            @RequestParam(required = false) Long cityId,
            @RequestParam(required = false) Long productCategoryId) {
        List<ProductDTO> products = productService.searchProducts(searchString, cityId, productCategoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get products of a seller
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<ProductDTO>> getProductsOfSeller(@PathVariable Long sellerId) {
        List<ProductDTO> products = productService.getProductsOfSeller(sellerId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Add a new product
    @PostMapping("/seller/{sellerId}")
    public ResponseEntity<String> addProduct(@PathVariable Long sellerId, @RequestBody ProductDTO productDTO) {
        String response = productService.addProduct(sellerId, productDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update an existing product
    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        String response = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Remove a product
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable Long productId) {
        String response = productService.removeProduct(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Add a review to a product
    @PostMapping("/{productId}/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long productId, @RequestBody ReviewDTO reviewDTO) {
        String response = productService.addReview(productId, reviewDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
