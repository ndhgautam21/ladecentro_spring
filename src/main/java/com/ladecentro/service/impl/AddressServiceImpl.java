package com.ladecentro.service.impl;

import com.ladecentro.entity.Address;
import com.ladecentro.entity.User;
import com.ladecentro.exception.GlobalException;
import com.ladecentro.models.response.AddressResponse;
import com.ladecentro.models.response.ErrorResponse;
import com.ladecentro.repository.AddressRepository;
import com.ladecentro.repository.UserRepository;
import com.ladecentro.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<AddressResponse> getAllAddresses(String userId) {

        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream().map(this::getAddressResponse).collect(Collectors.toList());
    }

    @Override
    public Address getAddress(String userId, String id) {

        Optional<Address> optional = addressRepository.findBy_idAndUserId(id, userId);
        if (optional.isEmpty()) {
            log.error(">>>> address not found for user id : {} and id : {}", userId, id);
            throw new GlobalException(HttpStatus.BAD_REQUEST, "address not found");
        }
        return optional.get();
    }

    @Override
    public AddressResponse createAddress(String userId, Address address) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            log.error(">>>> user not found for user id : {}", user);
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user not found");
        }
        address.setUserId(userId);
        return getAddressResponse(addressRepository.save(address));
    }

    @Override
    public Address updateAddress(String userId, String id, Address address) {

        Optional<Address> optional = addressRepository.findBy_idAndUserId(id, userId);
        if (optional.isEmpty()) {
            log.error(">>>> address not found for user id : {} and id : {}", userId, id);
            throw new GlobalException(HttpStatus.BAD_REQUEST, "address not found");
        }
        Address address1 = optional.get();
        if (address.getName() != null) {
            address1.setName(address.getName());
        }
        if (address.getPhoneNo() != null) {
            address1.setPhoneNo(address.getPhoneNo());
        }
        if (address.getAddress() != null) {
            address1.setAddress(address.getAddress());
        }
        if (address.getPinCode() != null) {
            address1.setPinCode(address.getPinCode());
        }
        if (address.getCity() != null) {
            address1.setCity(address.getCity());
        }
        if (address.getState() != null) {
            address1.setState(address.getState());
        }
        return addressRepository.save(address1);
    }

    @Override
    public ErrorResponse deleteAddress(String userId, String id) {

        Integer isDeleted = addressRepository.deleteBy_idAndUserId(id, userId);
        if (isDeleted == 0) {
            log.error(">>>> couldn't delete address");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "couldn't delete address");
        }
        ErrorResponse response = new ErrorResponse();
        response.setStatus(isDeleted);
        response.setMessage("Deleted successfully");
        return response;
    }

    private AddressResponse getAddressResponse(Address address) {

        String fullAddress = address.getAddress().concat(", ")
                .concat(address.getCity()).concat(", ")
                .concat(address.getState()).concat(" - ")
                .concat(address.getPinCode());
        AddressResponse response = new AddressResponse();
        response.setId(address.get_id());
        response.setName(address.getName());
        response.setAddress(fullAddress);
        response.setPhone_no(address.getPhoneNo());
        return response;
    }
}
