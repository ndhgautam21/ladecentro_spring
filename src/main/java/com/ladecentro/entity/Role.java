package com.ladecentro.entity;

import com.ladecentro.constant.Roles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
public class Role {

    private String _id;

    @Field(name = "name", targetType = FieldType.STRING)
    private Roles name;
}
