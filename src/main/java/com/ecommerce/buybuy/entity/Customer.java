package com.ecommerce.buybuy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CUSTOMER")
@Getter
@Setter
public class Customer extends User {
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private ShoppingCart shoppingCart;

    @Column(unique = true)
    private String phoneNumber;

    @Embedded
    private Address shippingAddress;

    @Override
    public String getUserType() {
        return "CUSTOMER";
    }
}
