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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @Autowired
    CustomUserService customUserService;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * user signup
     *
     * @param request signup request
     * @return JWT token
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {

        User user = userService.createUser(request);
        UserDetails userDetails = new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getRoles());
        log.info(">>>> user details : {}", userDetails);
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.get_id());
        String jwtToken = jwtUtil.generateToken(userDetails, claims);
        log.info(">>>> jwt token : {}", jwtToken);
        return ResponseEntity.ok(jwtToken);
    }

    /**
     * user login
     *
     * @param request login request
     * @return JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()));
        UserDetails userDetails = customUserService.loadUserByUsername(request.getEmail());
        User user = userService.getUserByEmail(userDetails.getUsername());
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.get_id());
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
