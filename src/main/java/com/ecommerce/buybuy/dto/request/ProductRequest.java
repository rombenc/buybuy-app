package com.ecommerce.buybuy.dto.request;

import com.ecommerce.buybuy.entity.Category;
import com.ecommerce.buybuy.entity.Seller;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String productCode;
    private String productName;
    private String imageUrl;
    private Long price;
    private Integer stock;
    private String brand;
    private String sellerName;
    private Long categoryId;
}
