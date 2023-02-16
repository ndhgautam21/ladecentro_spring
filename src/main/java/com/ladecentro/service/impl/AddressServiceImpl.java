package com.ladecentro.service.impl;

import com.ladecentro.entity.Address;
import com.ladecentro.entity.User;
import com.ladecentro.exception.GlobalException;
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

@Slf4j
@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Address> getAllAddresses(Long userId) {

        return addressRepository.findByUserId(userId);
    }

    @Override
    public Address getAddress(Long userId, Long id) {

        Optional<Address> optional = addressRepository.findByUserIdAndId(userId, id);
        if (optional.isEmpty()) {
            log.error(">>>> address not found for user id : {} and id : {}", userId, id);
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user not found");
        }
        return optional.get();
    }

    @Override
    public Address createAddress(Long userId, Address address) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            log.error(">>>> user not found for user id : {}", user);
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user not found");
        }
        address.setUser(user.get());
        return addressRepository.save(address);
    }

    @Override
    public ErrorResponse updateAddress(Long userId, Long id, Address address) {

        Optional<Address> optional = addressRepository.findByUserIdAndId(userId, id);
        if (optional.isEmpty()) {
            log.error(">>>> address not found for user id : {} and id : {}", userId, id);
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user not found");
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
        addressRepository.save(address1);
        return null;
    }

    @Override
    public ErrorResponse deleteAddress(Long userId, Long id) {

        return null;
    }
}
