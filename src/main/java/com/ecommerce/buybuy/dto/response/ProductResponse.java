package com.ecommerce.buybuy.dto.response;

import com.ecommerce.buybuy.entity.Category;
import com.ecommerce.buybuy.entity.Seller;

public class ProductResponse {
    private String productCode;
    private String productName;
    private String imageUrl;
    private Long price;
    private String stock;
    private String brand;
    private Seller seller;
    private Category category;
}
