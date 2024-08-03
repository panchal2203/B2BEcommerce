package com.b2b.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private Long buyerId;
    private Long sellerId;
    private String sellerName;
    private BigDecimal totalAmount;
    private List<CartItemDTO> cartItems;
}
