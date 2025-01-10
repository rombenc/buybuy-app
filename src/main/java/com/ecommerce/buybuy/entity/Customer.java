package com.ecommerce.buybuy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CUSTOMER")
@Getter
@Setter
public class Customer extends User {
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private Cart shoppingCart;

    @Column(unique = true)
    private String phoneNumber;

    @Embedded
    private Address shippingAddress;

    @Override
    public String getUserType() {
        return "CUSTOMER";
    }

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Payment> payments = new ArrayList<>();

}
