package com.example.efros.demo.jwt.service;

import com.example.efros.demo.jwt.domens.ERole;
import com.example.efros.demo.jwt.domens.Role;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Data
public class RoleService {
    private Set<Role> roles;

    public RoleService() {
        roles = new HashSet<>();
        //Account account=new Account(1L,"A","B", Industry.A,"C","D");
        roles.add(new Role(1, ERole.ROLE_ADMIN));
    }

    public Optional<Role> findByName(ERole roleUser) {
        for (Role role : roles) {
            if (role.getName() == roleUser) {
                return Optional.of(role);
            }
        }
        return null;
    }
}
