package com.ecommerce.buybuy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("SELLER")
@Getter
@Setter
public class Seller extends User {
    private String storeName;
    private String storeDescription;
    private String businessRegistrationNumber;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @Embedded
    private Address businessAddress;

    @Override
    public String getUserType() {
        return "SELLER";
    }
}