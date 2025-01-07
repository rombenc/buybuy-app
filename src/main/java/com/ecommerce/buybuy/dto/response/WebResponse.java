package com.ecommerce.buybuy.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse <T>{
    private Integer statusCode;
    private String message;
    private T data;
}
