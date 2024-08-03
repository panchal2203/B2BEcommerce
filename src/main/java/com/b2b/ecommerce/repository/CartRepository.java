package com.b2b.ecommerce.repository;

import com.b2b.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByBuyerId(Long buyerId);
    Cart findByBuyerIdAndSellerId(Long buyerId, Long sellerId);
}

