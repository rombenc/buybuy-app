package com.ecommerce.buybuy.entity;

import com.ecommerce.buybuy.constant.UserType;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_address_id")
    private Address businessAddress;

    @Override
    public UserType getUserType() {
        return UserType.SELLER;
    }
}