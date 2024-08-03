package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.OrderDTO;
import com.b2b.ecommerce.dto.PaymentDetailsDTO;

import java.util.List;

public interface OrderService {
    boolean placeOrder(Long cartId, PaymentDetailsDTO paymentDTO, Long addressId);
    OrderDTO viewOrderDetails(Long orderId);
    boolean cancelOrder(Long orderId);
    List<OrderDTO> getOrdersPlaced(Long accountId);
    List<OrderDTO> getOrdersReceived(Long sellerId);
    boolean updateOrderStatus(Long orderId, String status);
}

