package com.ladecentro.controller;

import com.ladecentro.entity.Address;
import com.ladecentro.exception.GlobalException;
import com.ladecentro.service.AddressService;
import com.ladecentro.util.UtilFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    UtilFunction utilFunction;

    @Autowired
    AddressService addressService;

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllAddresses(HttpServletRequest headers) {

        Long userId = utilFunction.getUserIdFromToken(headers);
        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        return ResponseEntity.ok(addressService.getAllAddresses(userId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAddress(HttpServletRequest headers, @PathVariable("id") Long id) {

        Long userId = utilFunction.getUserIdFromToken(headers);
        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        if (Objects.isNull(id)) {
            log.error(">>>> address id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "address id should not be null");
        }
        return ResponseEntity.ok(addressService.getAddress(userId, id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAddress(HttpServletRequest headers, @RequestBody Address address) {

        Long userId = utilFunction.getUserIdFromToken(headers);
        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        return ResponseEntity.ok(addressService.createAddress(userId, address));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAddress(HttpServletRequest headers, @PathVariable("id") Long id, @RequestBody Address address) {

        Long userId = utilFunction.getUserIdFromToken(headers);
        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        if (Objects.isNull(id)) {
            log.error(">>>> address id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "address id should not be null");
        }
        return ResponseEntity.ok(addressService.updateAddress(userId, id, address));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(HttpServletRequest headers, @PathVariable("id") Long id) {

        Long userId = utilFunction.getUserIdFromToken(headers);
        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        if (Objects.isNull(id)) {
            log.error(">>>> address id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "address id should not be null");
        }
        return ResponseEntity.ok(addressService.deleteAddress(userId, id));
    }
}
