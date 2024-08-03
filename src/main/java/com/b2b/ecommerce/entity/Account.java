package com.b2b.ecommerce.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailId;
    private String password;

    @OneToOne
    private Seller seller;

    @OneToMany(mappedBy = "account")
    private List<Address> addresses;
}
