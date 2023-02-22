package com.ladecentro.service;

import com.ladecentro.entity.Address;
import com.ladecentro.models.response.AddressResponse;
import com.ladecentro.models.response.ErrorResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> getAllAddresses(Long userId);

    Address getAddress(Long userId, Long id);

    Address createAddress(Long userId, Address address);

    Address updateAddress(Long userId, Long id, Address address);

    ErrorResponse deleteAddress(Long userId, Long id);

}
