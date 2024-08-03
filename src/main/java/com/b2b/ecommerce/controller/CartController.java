package com.b2b.ecommerce.controller;

import com.b2b.ecommerce.dto.CartDTO;
import com.b2b.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addOrUpdateItem")
    public ResponseEntity<String> addOrUpdateItemToCart(
            @RequestParam Long productId,
            @RequestParam Long accountId,
            @RequestParam Long quantity) {
        String status = cartService.addOrUpdateItemToCart(productId, accountId, quantity);
        return ResponseEntity.ok(status);
    }

    @DeleteMapping("/removeItem")
    public ResponseEntity<String> removeItemFromCart(
            @RequestParam Long cartItemId,
            @RequestParam Long productId) {
        String status = cartService.removeItemFromCart(cartItemId, productId);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<String> updateProductQuantityInCart(
            @RequestParam Long cartItemId,
            @RequestParam Long quantity) {
        String status = cartService.updateProductQuantityInCart(cartItemId, quantity);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/getCartDetails/{cartId}")
    public ResponseEntity<CartDTO> getCartDetails(@PathVariable Long cartId) {
        CartDTO cartDTO = cartService.getCartDetails(cartId);
        return ResponseEntity.ok(cartDTO);
    }

    @GetMapping("/getAllCarts/{accountId}")
    public ResponseEntity<List<CartDTO>> getAllCarts(@PathVariable Long accountId) {
        List<CartDTO> carts = cartService.getAllCarts(accountId);
        return ResponseEntity.ok(carts);
    }
}
