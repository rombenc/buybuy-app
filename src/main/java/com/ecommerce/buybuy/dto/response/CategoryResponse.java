package com.ecommerce.buybuy.dto.response;

import com.ecommerce.buybuy.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String name;
    private Product product;
}
