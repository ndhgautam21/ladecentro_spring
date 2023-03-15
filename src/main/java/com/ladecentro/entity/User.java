package com.ladecentro.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ladecentro.constant.Roles;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Document(collection = "user")
public class User extends Auditable {

    @Id
    private String _id;

    @Field(name = "email")
    @Indexed(unique = true)
    private String email;

    @Field(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Field(name = "name")
    private String name;

    @Field(name = "profile_image")
    @JsonProperty("profile_image")
    private Binary profileImage;

    private Set<Roles> roles;
}
