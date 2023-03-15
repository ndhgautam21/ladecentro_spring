package com.ladecentro.repository;

import com.ladecentro.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends MongoRepository<Address, String> {

    List<Address> findByUserId(String userId);

    Optional<Address> findBy_idAndUserId(String id, String userId);

    Integer deleteBy_idAndUserId(String id, String UserId);
}
