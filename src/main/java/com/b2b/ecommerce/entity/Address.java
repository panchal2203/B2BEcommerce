package com.b2b.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address1;
    private String address2;
    private String address3;
    private String zipCode;
    private String city;
    private String state;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
