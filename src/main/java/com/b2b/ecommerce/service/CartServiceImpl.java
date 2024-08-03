package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.CartDTO;
import com.b2b.ecommerce.dto.CartItemDTO;
import com.b2b.ecommerce.entity.Account;
import com.b2b.ecommerce.entity.Cart;
import com.b2b.ecommerce.entity.CartItem;
import com.b2b.ecommerce.entity.Product;
import com.b2b.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public String addOrUpdateItemToCart(Long productId, Long accountId, Long quantity) {
        // Find the account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Find the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Get seller ID from the product
        Long sellerId = product.getSeller().getId();

        // Find the cart for the specific seller
        Cart cart = cartRepository.findByBuyerIdAndSellerId(accountId, sellerId);

        if (cart == null) {
            // Create a new cart if none exists for the buyer and seller
            cart = new Cart();
            cart.setBuyer(account);
            cart.setSeller(sellerRepository.findById(sellerId)
                    .orElseThrow(() -> new RuntimeException("Seller not found")));
            cart.setTotalAmount(BigDecimal.ZERO);
            cart = cartRepository.save(cart);
        }

        // Find the cart item
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        if (cartItem == null) {
            // Create a new cart item
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setAmount(BigDecimal.valueOf(product.getMinPrice()).multiply(BigDecimal.valueOf(quantity)));
            cartItemRepository.save(cartItem);
        } else {
            // Update existing cart item
            cartItem.setQuantity(quantity);
            cartItem.setAmount(BigDecimal.valueOf(product.getMinPrice()).multiply(BigDecimal.valueOf(quantity)));
            cartItemRepository.save(cartItem);
        }

        // Update the total amount of the cart
        BigDecimal totalAmount = cartItemRepository.findAllByCartId(cart.getId())
                .stream()
                .map(CartItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);

        return "Item added or updated successfully!";
    }


    @Override
    public String removeItemFromCart(Long cartItemId, Long productId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItemRepository.delete(cartItem);
        Cart cart = cartItem.getCart();
        updateTotalAmount(cart);

        return "Item removed from cart successfully!";
    }

    @Override
    public String updateProductQuantityInCart(Long cartItemId, Long quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        Product product = cartItem.getProduct();
        cartItem.setQuantity(quantity);
        cartItem.setAmount(BigDecimal.valueOf(product.getMinPrice()).multiply(BigDecimal.valueOf(quantity)));

        cartItemRepository.save(cartItem);
        updateTotalAmount(cartItem.getCart());

        return "Quantity updated successfully!";
    }

    @Override
    public List<CartDTO> getAllCarts(Long accountId) {
        List<Cart> carts = cartRepository.findByBuyerId(accountId);
        List<CartDTO> cartDTOs = new ArrayList<>();

        for (Cart cart : carts) {
            CartDTO dto = new CartDTO();
            dto.setId(cart.getId());
            dto.setBuyerId(cart.getBuyer().getId());
            dto.setSellerId(cart.getSeller() != null ? cart.getSeller().getId() : null);
            dto.setSellerName(cart.getSeller() != null ? cart.getSeller().getName() : null); // Added seller name
            dto.setTotalAmount(cart.getTotalAmount());
            List<CartItemDTO> cartItemDTOs = getCartItemDTOS(cart);
            dto.setCartItems(cartItemDTOs);
            cartDTOs.add(dto);
        }
        return cartDTOs;
    }

    private static List<CartItemDTO> getCartItemDTOS(Cart cart) {
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setId(cartItem.getId());
            itemDTO.setProductName(cartItem.getProduct().getName()); // Added product name
            itemDTO.setProductDescription(cartItem.getProduct().getDescription()); // Added product description
            itemDTO.setQuantity(cartItem.getQuantity());
            itemDTO.setAmount(cartItem.getAmount());
            cartItemDTOs.add(itemDTO);
        }
        return cartItemDTOs;
    }

    public CartDTO getCartDetails(Long cartId) {
        // Find the cart by its ID
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Map the Cart entity to CartDTO
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setBuyerId(cart.getBuyer().getId());
        dto.setSellerId(cart.getSeller() != null ? cart.getSeller().getId() : null);
        dto.setSellerName(cart.getSeller() != null ? cart.getSeller().getCompanyName() : null); // Assuming 'companyName' is the seller's name
        dto.setTotalAmount(cart.getTotalAmount());

        // Convert CartItems to CartItemDTOs
        List<CartItemDTO> cartItemDTOs = cart.getCartItems().stream().map(item -> {
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setProductName(item.getProduct().getName());
            itemDTO.setProductDescription(item.getProduct().getDescription());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setAmount(item.getAmount());
            return itemDTO;
        }).collect(Collectors.toList());

        dto.setCartItems(cartItemDTOs);

        return dto;
    }



    private void updateTotalAmount(Cart cart) {
        BigDecimal totalAmount = cart.getCartItems().stream()
                .map(CartItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }
}
