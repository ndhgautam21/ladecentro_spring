package com.ladecentro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_no")
    @JsonProperty("phone_no")
    private String phoneNo;

    @Column(name = "address")
    private String address;

    @Column(name = "pin_code")
    @JsonProperty("pin_code")
    private String pinCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
}
