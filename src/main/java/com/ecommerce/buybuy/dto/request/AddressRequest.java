package com.ecommerce.buybuy.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
