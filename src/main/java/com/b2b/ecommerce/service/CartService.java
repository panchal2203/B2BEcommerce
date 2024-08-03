package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.CartDTO;

import java.util.List;

public interface CartService {

    String addOrUpdateItemToCart(Long productId, Long accountId, Long quantity);
    String removeItemFromCart(Long cartItemId, Long productId);
    String updateProductQuantityInCart(Long cartItemId, Long quantity);
    List<CartDTO> getAllCarts(Long accountId);
    CartDTO getCartDetails(Long cartId);
}
