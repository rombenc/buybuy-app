package com.ecommerce.buybuy.dto.response;

import com.ecommerce.buybuy.entity.Category;
import com.ecommerce.buybuy.entity.Product;
import com.ecommerce.buybuy.entity.Seller;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Product product;
    private String productId;
    private String productCode;
    private String productName;
    private String imageUrl;
    private Long price;
    private Integer stock;
    private String brand;
    private Seller seller;
    private Category category;

}
