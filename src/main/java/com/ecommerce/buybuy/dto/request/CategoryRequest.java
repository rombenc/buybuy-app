package com.ecommerce.buybuy.dto.request;

import com.ecommerce.buybuy.dto.response.ProductResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private Long id;
    private String name;
    private List<ProductResponse> productList;
}


