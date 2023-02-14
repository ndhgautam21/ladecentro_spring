package com.ladecentro.models.request;

import com.ladecentro.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class LoginRequest {

    private String email;
    private String password;
    //private Set<Role> roles;
}
