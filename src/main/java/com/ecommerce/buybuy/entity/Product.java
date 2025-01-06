package com.ecommerce.buybuy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;

    private String productName;

    private Long price;

    private Integer stock;

    private String brand;

    @ManyToOne
    private Seller seller;
}
