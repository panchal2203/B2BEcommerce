package com.b2b.ecommerce.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailId;
    private String password;
    private SellerDTO seller;
}
