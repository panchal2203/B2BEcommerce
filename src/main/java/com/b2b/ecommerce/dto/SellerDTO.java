package com.b2b.ecommerce.dto;

import lombok.*;

import java.util.List;

@Data
public class SellerDTO {
    private Long id;
    private String companyName;
    private String gstNumber;
    private String summary;
    private String address;
    private String mobileNumber;
    private String emailId;
    private String name;
    private Long cityId;
    private String cityName;
    private Long accountId;
    private String accountName; // Additional field for convenience
    private List<ProductDTO> products;
    public SellerDTO(Long id, String name, String companyName, String mobileNumber, String emailId) {
        this.id = id;
        this.name = name;
        this.companyName = companyName;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
    }
    public SellerDTO(){

    }
}
