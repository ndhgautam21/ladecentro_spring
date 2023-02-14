package com.ladecentro.models.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private Integer status;
    private String message;
    private String date_time;
}
