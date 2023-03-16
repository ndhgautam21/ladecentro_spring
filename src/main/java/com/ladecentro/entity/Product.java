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
@Document(collection = "product")
public class Product extends Auditable {

    @Id
    private String _id;

    @JsonProperty("product_name")
    @Field(name = "product_name")
    private String productName;

    @JsonProperty("product_image")
    @Field(name = "product_image")
    private String productImage;

    @JsonProperty("mrp")
    @Field(name = "mrp")
    private Double mrp;

    @JsonProperty("selling_price")
    @Field(name = "selling_price")
    private Double sellingPrice;

    @JsonProperty("sku")
    @Field(name = "sku")
    private String sku;

    @JsonProperty("description")
    @Field(name = "description")
    private String description;

    @JsonProperty("category_id")
    @Field(name = "category_id")
    private String categoryId;

    @JsonProperty("tag")
    @Field(name = "tag")
    private String tag;
}
