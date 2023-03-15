package com.ladecentro.controller;

import com.ladecentro.exception.GlobalException;
import com.ladecentro.models.request.UserUpdateRequest;
import com.ladecentro.security.JwtUtil;
import com.ladecentro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @GetMapping(value = "/get")
    @PreAuthorize("hasAnyRole('USER', 'DESIGNER')")
    public ResponseEntity<?> getUserByEmail(HttpServletRequest headers) {

        String token = headers.getHeader("x-auth-token");
        String email = jwtUtil.extractUsername(token);
        log.info(">>>> start getting user by email : {}", email);
        return ResponseEntity.ok(userService.getUserByEmail(Optional
                .ofNullable(email)
                .orElseThrow(() -> new GlobalException(HttpStatus.BAD_REQUEST, "Email should not be null"))));
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasAnyRole('USER', 'DESIGNER')")
    public ResponseEntity<?> updateUser(HttpServletRequest headers, @RequestBody UserUpdateRequest request) {

        String token = headers.getHeader("x-auth-token");
        String email = jwtUtil.extractUsername(token);
        log.info(">>>> start updating user by email : {}, request : {}", email, request);
        return ResponseEntity.ok(userService.updateUser(request, Optional
                .ofNullable(email)
                .orElseThrow(() -> new GlobalException(HttpStatus.BAD_REQUEST, "Email should not be null"))));
    }

    @PutMapping(value = "/profile-image")
    public ResponseEntity<?> updateProfileImage(HttpServletRequest headers, @RequestPart(required = false) MultipartFile file) {

        String token = headers.getHeader("x-auth-token");
        String email = jwtUtil.extractUsername(token);
        log.info(">>>> start updating profile-image by email : {}, file : {}", email, file);
        return ResponseEntity.ok(userService.updateProfileImage(Optional
                .ofNullable(email)
                .orElseThrow(() -> new GlobalException(HttpStatus.BAD_REQUEST, "Email should not be null")), file));
    }

    @DeleteMapping(value = "/delete")
    @PreAuthorize("hasAnyRole('USER', 'DESIGNER')")
    public ResponseEntity<?> deleteUser(HttpServletRequest headers) {

        String token = headers.getHeader("x-auth-token");
        String email = jwtUtil.extractUsername(token);
        return ResponseEntity.ok(userService.deleteUser(Optional
                .ofNullable(email)
                .orElseThrow(() -> new GlobalException(HttpStatus.BAD_REQUEST, "Email should not be null"))));
    }

    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }
}
