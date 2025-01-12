package com.ecommerce.buybuy.service.Impl;

import com.ecommerce.buybuy.dto.request.AddressRequest;
import com.ecommerce.buybuy.dto.response.AddressResponse;
import com.ecommerce.buybuy.dto.response.WebResponse;
import com.ecommerce.buybuy.entity.Address;
import com.ecommerce.buybuy.entity.Customer;
import com.ecommerce.buybuy.entity.Seller;
import com.ecommerce.buybuy.entity.User;
import com.ecommerce.buybuy.repository.AddressRepository;
import com.ecommerce.buybuy.repository.CustomerRepository;
import com.ecommerce.buybuy.repository.SellerRepository;
import com.ecommerce.buybuy.repository.UserRepository;
import com.ecommerce.buybuy.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;

    public WebResponse<String> updateAddress(AddressRequest addressRequest) {
        try {
            // Ambil email dari JWT token
            String email = jwtService.extractUsername(
                    SecurityContextHolder.getContext().getAuthentication().getCredentials().toString()
            );

            Customer customer = (Customer) customerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Address newAddress = new Address();
            newAddress.setStreet(addressRequest.getStreet());
            newAddress.setCity(addressRequest.getCity());
            newAddress.setState(addressRequest.getState());
            newAddress.setCountry(addressRequest.getCountry());
            newAddress.setPostalCode(addressRequest.getPostalCode());

            customer.setShippingAddress(newAddress); // Set alamat baru
            customerRepository.save(customer); // Simpan perubahan

            return new WebResponse<>(200, "Address updated successfully", "Updated");
        } catch (Exception e) {
            return new WebResponse<>(500, "Error: " + e.getMessage(), null);
        }
    }



}
