package com.ladecentro.controller;

import com.ladecentro.entity.Address;
import com.ladecentro.exception.GlobalException;
import com.ladecentro.service.AddressService;
import com.ladecentro.util.UtilFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * get all addresses
     *
     * @param headers headers
     * @return list of address
     */
    @GetMapping("/get")
    public ResponseEntity<?> getAllAddresses(HttpServletRequest headers) {

        String userId = utilFunction.getUserIdFromToken(headers);
        log.info(">>>> start getting addresses for user id : {}", userId);
        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        return ResponseEntity.ok(addressService.getAllAddresses(userId));
    }

    /**
     * get address by id
     *
     * @param headers headers
     * @param id      address_id
     * @return Address
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAddress(HttpServletRequest headers, @PathVariable("id") String id) {

        String userId = utilFunction.getUserIdFromToken(headers);
        log.info(">>>> start getting address for user_id : {} and address_id : {}", userId, id);
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

    /**
     * create address
     *
     * @param headers headers
     * @param address address
     * @return Address response
     */
    @PostMapping("/create")
    public ResponseEntity<?> createAddress(HttpServletRequest headers, @RequestBody Address address) {

        String userId = utilFunction.getUserIdFromToken(headers);
        log.info(">>>> start creating address : {} for user_id : {}", address, userId);
        if (Objects.isNull(userId)) {
            log.error(">>>> User id should not be null");
            throw new GlobalException(HttpStatus.BAD_REQUEST, "user id should not be null");
        }
        return ResponseEntity.ok(addressService.createAddress(userId, address));
    }

    /**
     * update address by id
     *
     * @param headers headers
     * @param id      address_id
     * @param address address
     * @return address
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAddress(HttpServletRequest headers, @PathVariable("id") String id, @RequestBody Address address) {

        String userId = utilFunction.getUserIdFromToken(headers);
        log.info(">>>> start updating address : {} for user_id : {} and address_id : {}", address, userId, id);
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

    /**
     * delete address by id
     *
     * @param headers headers
     * @param id      address_id
     * @return generic response
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(HttpServletRequest headers, @PathVariable("id") String id) {

        String userId = utilFunction.getUserIdFromToken(headers);
        log.info(">>>> start deleting address for user_id : {} and address_id : {}", userId, id);
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
