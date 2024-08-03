package com.b2b.ecommerce.repository;

import com.b2b.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndProductId(Long cartId, Long productId);
    void deleteByCartId(Long cartId);
    List<CartItem> findAllByCartId(Long cartId);
}

