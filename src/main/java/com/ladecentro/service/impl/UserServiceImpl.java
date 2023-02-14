package com.ladecentro.service.impl;

import com.ladecentro.entity.User;
import com.ladecentro.exception.GlobalException;
import com.ladecentro.models.request.SignupRequest;
import com.ladecentro.repository.UserRepository;
import com.ladecentro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createUser(SignupRequest request) {

        Optional<User> optional = userRepository.findByEmail(request.getEmail());
        if (optional.isPresent()) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "Email already exist");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {

        Optional<User> optional = userRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "User not found");
        }
        return optional.get();
    }
}
