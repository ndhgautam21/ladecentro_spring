package com.ladecentro.entity;

import com.ladecentro.constant.Roles;
import javax.persistence.*;
import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Roles name;
}
