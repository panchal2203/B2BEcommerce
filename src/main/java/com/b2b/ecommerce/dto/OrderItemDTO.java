package com.b2b.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private long id;
    private String productName;
    private String productDescription;
    private long quantity;
    private BigDecimal amount;
}