package com.ladecentro.models.request;

import com.ladecentro.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {

    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
}
