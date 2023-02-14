package com.ladecentro.service;

import com.ladecentro.entity.User;
import com.ladecentro.models.request.SignupRequest;

public interface UserService {

    void createUser(SignupRequest user);

    User getUserByEmail(String email);
}
