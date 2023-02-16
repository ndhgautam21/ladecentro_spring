package com.ladecentro.controller;

import com.ladecentro.entity.User;
import com.ladecentro.models.request.LoginRequest;
import com.ladecentro.models.request.SignupRequest;
import com.ladecentro.security.CustomUserService;
import com.ladecentro.security.JwtUtil;
import com.ladecentro.security.UserDetailsImpl;
import com.ladecentro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "")
public class AuthController {

    @Autowired JwtUtil jwtUtil;

    @Autowired UserService userService;

    @Autowired CustomUserService customUserService;

    @Autowired AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {

        User user = userService.createUser(request);
        UserDetails userDetails = new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getRoles());

        log.info(">>>> user details : {}", userDetails);
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.getId());
        String jwtToken = jwtUtil.generateToken(userDetails, claims);

        log.info(">>>> jwt token : {}", jwtToken);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));
        UserDetails userDetails = customUserService.loadUserByUsername(loginRequest.getEmail());

        User user = userService.getUserByEmail(userDetails.getUsername());
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.getId());
        String token = jwtUtil.generateToken(userDetails, claims);

        log.info(">>>> jwt token : {}", token);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> forAdmin() {

        return ResponseEntity.ok("Hello ADMIN");
    }

    @GetMapping("/designer")
    @PreAuthorize("hasRole('DESIGNER')")
    public ResponseEntity<?> forDesigner() {

        return ResponseEntity.ok("Hello DESIGNER");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> forUser() {

        return ResponseEntity.ok("Hello USER");
    }
}
