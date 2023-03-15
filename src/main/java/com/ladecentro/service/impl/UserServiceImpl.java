package com.ladecentro.service.impl;

import com.ladecentro.entity.User;
import com.ladecentro.exception.GlobalException;
import com.ladecentro.models.request.SignupRequest;
import com.ladecentro.models.request.UserUpdateRequest;
import com.ladecentro.models.response.ErrorResponse;
import com.ladecentro.repository.UserRepository;
import com.ladecentro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createUser(SignupRequest request) {

        Optional<User> optional = userRepository.findByEmail(request.getEmail());
        if (optional.isPresent()) {
            log.error(">>>> Email already exist : {}", request.getEmail());
            throw new GlobalException(HttpStatus.BAD_REQUEST, "Email already exist");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        log.info(">>>> user info save to DB is : {}", user);
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {

        Optional<User> optional = userRepository.findByEmail(email);
        if (optional.isEmpty()) {
            log.error(">>>> User not found : {}", email);
            throw new GlobalException(HttpStatus.NOT_FOUND, "User not found");
        }
        return optional.get();
    }

    @Override
    public User updateProfileImage(String email, MultipartFile file) {

        Optional<User> optional = userRepository.findByEmail(email);
        if (optional.isEmpty()) {
            log.error(">>>> User not found : {}", email);
            throw new GlobalException(HttpStatus.BAD_REQUEST, "User not found");
        }
        User user = optional.get();
        log.info(">>>> user from DB : {}", user);
        if (!Objects.isNull(file)) {
            try {
                user.setProfileImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            } catch (Exception e) {
                log.error(">>>> Error in converting image to binary file : {}", e.getMessage());
                throw new GlobalException(HttpStatus.NOT_ACCEPTABLE, "File type is not acceptable");
            }
        } else user.setProfileImage(null);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdateRequest request, String email) {

        Optional<User> optional = userRepository.findByEmail(email);
        if (optional.isEmpty()) {
            log.error(">>>> User not found : {}", email);
            throw new GlobalException(HttpStatus.BAD_REQUEST, "User not found");
        }
        User user = optional.get();
        log.info(">>>> user from DB : {}", user);
        if (!Objects.isNull(request.getName())) {
            user.setName(request.getName());
        }
        return userRepository.save(user);
    }

    @Override
    public ErrorResponse deleteUser(String email) {
        Optional<User> optional = userRepository.findByEmail(email);
        if (optional.isEmpty()) {
            log.error(">>>> User not found : {}", email);
            throw new GlobalException(HttpStatus.BAD_REQUEST, "User not found");
        }
        User user = optional.get();
        log.info(">>>> user from DB : {}", user);
        userRepository.deleteById(user.get_id());
        return new ErrorResponse(HttpStatus.OK.value(), "user deleted successfully", new Date().toString());
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
