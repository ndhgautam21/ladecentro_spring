package com.ladecentro.controller;

import com.ladecentro.exception.GlobalException;
import com.ladecentro.models.request.UserUpdateRequest;
import com.ladecentro.security.JwtUtil;
import com.ladecentro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "")
public class UserController {

    @Autowired JwtUtil jwtUtil;

    @Autowired UserService userService;

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('USER', 'DESIGNER')")
    public ResponseEntity<?> getUserByEmail(HttpServletRequest headers) {
        String token = headers.getHeader("x-auth-token");
        String email = jwtUtil.extractUsername(token);
        return ResponseEntity.ok(userService.getUserByEmail(Optional
                .ofNullable(email)
                .orElseThrow(() -> new GlobalException(HttpStatus.BAD_REQUEST, "Email should not be null"))));

    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('USER', 'DESIGNER')")
    public ResponseEntity<?> updateUser(HttpServletRequest headers, @RequestBody UserUpdateRequest request) {
        String token = headers.getHeader("x-auth-token");
        String email = jwtUtil.extractUsername(token);
        return ResponseEntity.ok(userService.updateUser(request, Optional
                .ofNullable(email)
                .orElseThrow(() -> new GlobalException(HttpStatus.BAD_REQUEST, "Email should not be null"))));

    }

    @DeleteMapping("/delete")
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
