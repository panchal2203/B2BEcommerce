package com.b2b.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private long id;
    private String address1;
    private String address2;
    private String address3;
    private String zipCode;
    private String city;
    private String state;
    private long accountId;
}
