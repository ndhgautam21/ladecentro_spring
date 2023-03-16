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
@Document(collection = "category")
public class Category extends Auditable {

    @Id
    private String _id;

    @Field(name = "name")
    @JsonProperty("name")
    private String name;

    @Field(name = "image")
    @JsonProperty("image")
    private String image;

    @Field(name = "parent_id")
    @JsonProperty("parent_id")
    private String parentId;
}
