package com.ecommerce.buybuy.service.inter;

import com.ecommerce.buybuy.entity.Address;
import com.ecommerce.buybuy.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
