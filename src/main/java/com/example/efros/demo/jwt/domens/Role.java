package com.example.efros.demo.jwt.domens;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Role {

    private Integer id;


    private ERole name;

    public Role() {
    }


}