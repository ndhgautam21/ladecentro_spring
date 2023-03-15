package com.ladecentro.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Document(collection = "address")
public class Address extends Auditable {

    @Id
    private String _id;

    @Field(name = "name")
    private String name;

    @Field(name = "phone_no")
    @JsonProperty("phone_no")
    private String phoneNo;

    @Field(name = "address")
    private String address;

    @Field(name = "pin_code")
    @JsonProperty("pin_code")
    private String pinCode;

    @Field(name = "city")
    private String city;

    @Field(name = "state")
    private String state;

    @Field(name = "user_id")
    private String userId;
}
