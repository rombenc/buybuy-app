package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.request.AddressRequest;
import com.ecommerce.buybuy.entity.Address;
import com.ecommerce.buybuy.entity.Seller;
import com.ecommerce.buybuy.repository.SellerRepository;
import com.ecommerce.buybuy.service.inter.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerAddressServiceImpl {
    private final SellerRepository sellerRepository;
    private AddressService addressService;

    public Address updateBusinessAddress(String sellerEmail, AddressRequest addressRequest) {
        Seller seller = (Seller) sellerRepository.findByEmail(sellerEmail)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Address address = seller.getBusinessAddress();
        if (address == null) {
            address = new Address();
        }
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(addressRequest.getCountry());
        address.setPostalCode(addressRequest.getPostalCode());
        seller.setBusinessAddress(addressService.saveAddress(address));
        sellerRepository.save(seller);

        return address;
    }
}
