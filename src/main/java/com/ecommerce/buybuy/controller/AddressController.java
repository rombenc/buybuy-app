package com.ecommerce.buybuy.controller;

import com.ecommerce.buybuy.dto.request.AddressRequest;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.service.Impl.AddressServiceImpl;
import com.ecommerce.buybuy.service.Impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressServiceImpl addressService;

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER', 'CUSTOMER')")
    public ResponseEntity<WebResponse<String>> updateAddress(@RequestBody AddressRequest addressRequest) {
        return ResponseEntity.ok(addressService.updateAddress(addressRequest));
    }

}
