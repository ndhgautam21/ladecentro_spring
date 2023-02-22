package com.ladecentro.models.response;

import lombok.Data;

@Data
public class AddressResponse {

    private Long id;
    private String name;
    private String address;
    private String phone_no;
}
