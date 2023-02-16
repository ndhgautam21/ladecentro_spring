package com.ladecentro.controller;

import com.ladecentro.entity.Address;
import com.ladecentro.exception.GlobalException;
import com.ladecentro.repository.AddressRepository;
import com.ladecentro.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    AddressService addressService;

    /**
     * get all addresses
     *
     * @param userId userId
     * @return ResponseEntity
     */
    @GetMapping("/get_all/{user_id}")
    public ResponseEntity<?> getAllAddresses(@PathVariable("user_id") Long userId) {

        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        return ResponseEntity.ok(addressService.getAllAddresses(userId));
    }

    @GetMapping("/get/{user_id}/{id}")
    public ResponseEntity<?> getAddress(@PathVariable("user_id") Long userId, @PathVariable("user_id") Long id) {

        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        if (Objects.isNull(id)) {
            log.error(">>>> address id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "address id should not be null");
        }
        return ResponseEntity.ok("");
    }

    @PostMapping("/create/{user_id}")
    public ResponseEntity<?> createAddress(@PathVariable("user_id") Long userId, @RequestBody Address address) {

        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        return ResponseEntity.ok(addressService.createAddress(userId, address));
    }

    @PutMapping("/update/{user_id}/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("user_id") Long userId, @PathVariable("id") Long id) {

        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        if (Objects.isNull(id)) {
            log.error(">>>> address id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "address id should not be null");
        }

        return ResponseEntity.ok("");
    }

    @DeleteMapping("/delete/{user_id}/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("user_id") Long userId, @PathVariable("id") Long id) {

        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        if (Objects.isNull(id)) {
            log.error(">>>> address id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "address id should not be null");
        }
        return ResponseEntity.ok("");
    }
}
