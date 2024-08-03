package com.b2b.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double mrp;
    private Double minPrice;

    @ManyToOne
    private Seller seller;

    private String sellerName;
    private String sellerCityName;
    private Long cityId;

    @ManyToOne
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;
}
