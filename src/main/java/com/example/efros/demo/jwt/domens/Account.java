package com.example.efros.demo.jwt.domens;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private Industry industry;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();

    public Account(int i, String firstName, String lastName, Industry industry, String username, String encode) {
        this.id = i;
        this.firstName = firstName;
        this.lastName = lastName;
        this.industry = industry;
        this.username = username;
        this.password = encode;
    }
}
