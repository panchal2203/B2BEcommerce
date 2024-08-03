package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.ProductDTO;
import com.b2b.ecommerce.dto.ReviewDTO;
import com.b2b.ecommerce.entity.*;
import com.b2b.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setMrp(product.getMrp());
            dto.setMinPrice(product.getMinPrice());
            dto.setSellerId(product.getSeller().getId());
            dto.setProductCategoryId(product.getProductCategory().getId());
            productDTOs.add(dto);
        }
        return productDTOs;
    }

    @Override
    public ProductDTO getProductDetails(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setMrp(product.getMrp());
        dto.setMinPrice(product.getMinPrice());
        dto.setSellerId(product.getSeller().getId());
        dto.setProductCategoryId(product.getProductCategory().getId());
        return dto;
    }

    @Override
    public List<ProductDTO> searchProducts(String searchString, Long cityId, Long productCategoryId) {
        List<Product> products = productRepository.findByNameContainingAndCityIdAndProductCategoryId(searchString, cityId, productCategoryId);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setMrp(product.getMrp());
            dto.setMinPrice(product.getMinPrice());
            dto.setSellerId(product.getSeller().getId());
            dto.setProductCategoryId(product.getProductCategory().getId());
            productDTOs.add(dto);
        }
        return productDTOs;
    }

    @Override
    public List<ProductDTO> getProductsOfSeller(Long sellerId) {
        List<Product> products = productRepository.findBySellerId(sellerId);
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setMrp(product.getMrp());
            dto.setMinPrice(product.getMinPrice());
            dto.setSellerId(product.getSeller().getId());
            dto.setProductCategoryId(product.getProductCategory().getId());
            productDTOs.add(dto);
        }
        return productDTOs;
    }

    @Override
    public String addProduct(Long sellerId, ProductDTO productDTO) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        ProductCategory category = productCategoryRepository.findById(productDTO.getProductCategoryId())
                .orElseThrow(() -> new RuntimeException("Product category not found"));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setMrp(productDTO.getMrp());
        product.setMinPrice(productDTO.getMinPrice());
        product.setSeller(seller);
        product.setProductCategory(category);
        product.setSellerName(seller.getName());
        product.setSellerCityName(seller.getCity().getCityName());
        product.setCityId(productDTO.getCityId());
        productRepository.save(product);
        return "Product added successfully!";
    }

    @Override
    public String updateProduct(Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setMrp(productDTO.getMrp());
        product.setMinPrice(productDTO.getMinPrice());

        productRepository.save(product);
        return "Product updated successfully!";
    }

    @Override
    public String removeProduct(Long productId) {
        productRepository.deleteById(productId);
        return "Product removed successfully!";
    }

    @Override
    public String addReview(Long productId, ReviewDTO reviewDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Review review = new Review();
        review.setRating(reviewDTO.getRating());
        review.setReviewComment(reviewDTO.getReviewComment());
        review.setProduct(product);
        // Assuming Account is the buyer who is providing the review
        Account buyer = accountRepository.findById(reviewDTO.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        review.setBuyer(buyer);

        reviewRepository.save(review);
        return "Review added successfully!";
    }
}
