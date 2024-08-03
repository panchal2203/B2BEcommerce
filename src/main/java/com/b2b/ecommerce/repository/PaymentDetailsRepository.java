package com.b2b.ecommerce.repository;

import com.b2b.ecommerce.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepository extends JpaRepository<Payment, Long> {
}
