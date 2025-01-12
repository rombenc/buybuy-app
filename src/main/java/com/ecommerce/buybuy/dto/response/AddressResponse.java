package com.ecommerce.buybuy.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddressResponse {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
