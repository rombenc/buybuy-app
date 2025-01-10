package com.ecommerce.buybuy.dto.request;

import com.ecommerce.buybuy.entity.Category;
import com.ecommerce.buybuy.entity.Seller;

public class CreateProductRequest {
    private String productCode;
    private String productName;
    private String imageUrl;
    private Long price;
    private String stock;
    private String brand;
    private Seller seller;
    private Category category;
}
