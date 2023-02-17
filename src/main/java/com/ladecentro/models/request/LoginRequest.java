package com.ladecentro.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    @NotNull(message = "email should not be null")
    @NotBlank(message = "email should not be blank")
    private String email;

    @NotNull(message = "password should not be null")
    @NotBlank(message = "password should not be blank")
    private String password;
}
