package com.ladecentro.controller;

import com.ladecentro.exception.GlobalException;
import com.ladecentro.security.JwtUtil;
import com.ladecentro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "")
public class UserController {

    @Autowired JwtUtil jwtUtil;

    @Autowired UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getUserByEmail(HttpServletRequest headers) {

        String token = headers.getHeader("x-auth-token");
        String email = jwtUtil.extractUsername(token);

        return ResponseEntity.ok(userService.getUserByEmail(Optional
                .ofNullable(email)
                .orElseThrow(() -> new GlobalException(HttpStatus.BAD_REQUEST, "Email should not be null"))));

    }
}
