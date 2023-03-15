package com.ladecentro.service;

import com.ladecentro.entity.Address;
import com.ladecentro.models.response.AddressResponse;
import com.ladecentro.models.response.ErrorResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> getAllAddresses(String userId);

    Address getAddress(String userId, String id);

    AddressResponse createAddress(String userId, Address address);

    Address updateAddress(String userId, String id, Address address);

    ErrorResponse deleteAddress(String userId, String id);

}
