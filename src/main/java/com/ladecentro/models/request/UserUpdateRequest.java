package com.ladecentro.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdateRequest {

    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be blank")
    private String name;
}