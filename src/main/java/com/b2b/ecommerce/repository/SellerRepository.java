package com.b2b.ecommerce.repository;

import com.b2b.ecommerce.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
