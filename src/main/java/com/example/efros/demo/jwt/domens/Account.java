package com.example.efros.demo.jwt.domens;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private Long id;
    private String firstName;
    private String LastName;
    private Industry industry;
    private String username;
    private String password;
}
