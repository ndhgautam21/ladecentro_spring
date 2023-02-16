package com.ladecentro.service;

import com.ladecentro.entity.Address;
import com.ladecentro.models.response.ErrorResponse;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses(Long userId);

    Address getAddress(Long userId, Long id);

    Object createAddress(Long userId, Address address);

    ErrorResponse updateAddress(Long userId, Long id, Address address);

    ErrorResponse deleteAddress(Long userId, Long id);

}
