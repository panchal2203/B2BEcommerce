package com.b2b.ecommerce.service;


import com.b2b.ecommerce.Enum.OrderStatus;
import com.b2b.ecommerce.Enum.PaymentStatus;
import com.b2b.ecommerce.Enum.PaymentType;
import com.b2b.ecommerce.dto.OrderDTO;
import com.b2b.ecommerce.dto.PaymentDetailsDTO;
import com.b2b.ecommerce.entity.*;
import com.b2b.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AddressRepository addressRepository;

    public boolean placeOrder(Long cartId, PaymentDetailsDTO paymentDTO, Long addressId) {
        // Retrieve and validate the cart
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Validate address
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // Save payment details
        Payment payment = new Payment();
        payment.setPaymentType(PaymentType.valueOf(paymentDTO.getPaymentType()));
        payment.setPaymentStatus(PaymentStatus.PENDING);
        paymentRepository.save(payment);

        // Create and save the order
        Order order = new Order();
        order.setBuyer(cart.getBuyer());
        order.setSeller(cart.getSeller());
        order.setTotalAmount(cart.getTotalAmount());
        order.setAddress(address);
        order.setPayment(payment);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderItems(cart.getCartItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setAmount(cartItem.getAmount());
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList()));
        orderRepository.save(order);

        // Clear the cart after placing the order
        cartRepository.delete(cart);

        return true;
    }

    @Override
    public OrderDTO viewOrderDetails(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return mapToDTO(order.get());
        }
        return null; // Handle not found scenario
    }

    @Override
    public boolean cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.setOrderStatus(OrderStatus.CANCELED);
            orderRepository.save(existingOrder);
            return true;
        }
        return false;
    }

    @Override
    public List<OrderDTO> getOrdersPlaced(Long accountId) {
        List<Order> orders = orderRepository.findByBuyerId(accountId);
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersReceived(Long sellerId) {
        List<Order> orders = orderRepository.findBySellerId(sellerId);
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean updateOrderStatus(Long orderId, String status) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.setOrderStatus(OrderStatus.valueOf(status));
            orderRepository.save(existingOrder);
            return true;
        }
        return false;
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setBuyerId(order.getBuyer().getId());
        dto.setSellerId(order.getSeller() != null ? order.getSeller().getId() : null);
        dto.setAddressId(order.getAddress() != null ? order.getAddress().getId() : null);
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderStatus(order.getOrderStatus().name());
        // Map PaymentDTO if needed
        return dto;
    }
}
