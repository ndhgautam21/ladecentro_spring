package com.ladecentro.security;

import com.ladecentro.entity.User;
import com.ladecentro.exception.GlobalException;
import com.ladecentro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "User not found"));
        return new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getRoles());
    }
}
