package com.ladecentro.models.request;

import com.ladecentro.entity.Role;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class SignupRequest {

    @NotNull(message = "email should not be null")
    @NotBlank(message = "email should not be blank")
    private String email;

    @NotNull(message = "password should not be null")
    @NotBlank(message = "password should not be blank")
    private String password;

    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be blank")
    private String name;

    @Valid
    @NotNull(message = "roles should not be null")
    @NotEmpty(message = "select at least one role")
    private Set<Role> roles;
}
