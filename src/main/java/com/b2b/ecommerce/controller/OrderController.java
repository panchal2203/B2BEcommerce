package com.b2b.ecommerce.controller;


import com.b2b.ecommerce.dto.OrderDTO;
import com.b2b.ecommerce.dto.PaymentDetailsDTO;
import com.b2b.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestParam Long cartId, @RequestBody PaymentDetailsDTO paymentDTO, @RequestParam Long addressId) {
        boolean success = orderService.placeOrder(cartId, paymentDTO, addressId);
        if (success) {
            return ResponseEntity.ok("Order placed successfully!");
        } else {
            return ResponseEntity.status(500).body("Failed to place the order.");
        }
    }

    @GetMapping("/viewOrder/{orderId}")
    public ResponseEntity<OrderDTO> viewOrderDetails(@PathVariable Long orderId) {
        OrderDTO orderDTO = orderService.viewOrderDetails(orderId);
        if (orderDTO != null) {
            return ResponseEntity.ok(orderDTO);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/cancelOrder/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        boolean success = orderService.cancelOrder(orderId);
        if (success) {
            return ResponseEntity.ok("Order canceled successfully!");
        } else {
            return ResponseEntity.status(500).body("Failed to cancel the order.");
        }
    }

    @GetMapping("/getOrdersPlaced")
    public ResponseEntity<List<OrderDTO>> getOrdersPlaced(@RequestParam Long accountId) {
        List<OrderDTO> orders = orderService.getOrdersPlaced(accountId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/getOrdersReceived")
    public ResponseEntity<List<OrderDTO>> getOrdersReceived(@RequestParam Long sellerId) {
        List<OrderDTO> orders = orderService.getOrdersReceived(sellerId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/updateOrderStatus/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        boolean success = orderService.updateOrderStatus(orderId, status);
        if (success) {
            return ResponseEntity.ok("Order status updated successfully!");
        } else {
            return ResponseEntity.status(500).body("Failed to update order status.");
        }
    }
}
