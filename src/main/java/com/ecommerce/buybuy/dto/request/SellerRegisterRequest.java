package com.ecommerce.buybuy.dto.request;

import com.ecommerce.buybuy.entity.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SellerRegisterRequest extends UserRegisterRequest {
    @NotBlank(message = "Store name is required")
    private String storeName;
    private String storeDescription;
    @NotBlank(message = "Business registration number is required")
    private String businessRegistrationNumber;
    private Address businessAddress;
}
