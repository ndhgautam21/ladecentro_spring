package com.ladecentro.service;

import com.ladecentro.entity.User;
import com.ladecentro.models.request.SignupRequest;
import com.ladecentro.models.request.UserUpdateRequest;
import com.ladecentro.models.response.ErrorResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    User createUser(SignupRequest user);

    User getUserByEmail(String email);

    User updateUser(UserUpdateRequest request, String email);

    User updateProfileImage(String email, MultipartFile file);

    ErrorResponse deleteUser(String user);

    List<User> getAllUsers();
}
