package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.OrderDTO;
import com.b2b.ecommerce.dto.ProductDTO;
import com.b2b.ecommerce.dto.SellerDTO;

import java.util.List;

public interface SellerService {
    boolean addOrUpdateSeller(SellerDTO sellerDTO);
    SellerDTO getSellerDetails(Long sellerId);
    List<SellerDTO> getAllSellers();
}
