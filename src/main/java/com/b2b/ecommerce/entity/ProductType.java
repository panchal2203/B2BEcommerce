package com.b2b.ecommerce.entity;

import com.b2b.ecommerce.Enum.MeasuringUnit;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String code;

    @ManyToOne
    private ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    private MeasuringUnit measuringUnit;

}
