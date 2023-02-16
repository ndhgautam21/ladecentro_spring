package com.ladecentro.service;

import com.ladecentro.entity.User;
import com.ladecentro.models.request.SignupRequest;
import com.ladecentro.models.request.UserUpdateRequest;
import com.ladecentro.models.response.ErrorResponse;

import java.util.List;

public interface UserService {

    void createUser(SignupRequest user);

    User getUserByEmail(String email);

    User updateUser(UserUpdateRequest request, String email);

    ErrorResponse deleteUser(String user);

    List<User> getAllUsers();
}
