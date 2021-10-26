package com.example.efros.demo.jwt.web;

import com.example.efros.demo.jwt.domens.Account;
import com.example.efros.demo.jwt.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity
                .ok(accountService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Account> findById(
            @PathVariable Integer id)  {
        return ResponseEntity.
                ok(accountService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Account> save(
            @RequestBody Account department) {
        return ResponseEntity
                .ok(accountService.save(department));
    }

//    @PutMapping("{id}")
//    public ResponseEntity<Account> update(
//            @PathVariable Integer id,
//            @RequestBody Account department) {
//        return ResponseEntity
//                .ok(departmentService.update(id, department));
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<Account> delete(
            @PathVariable Integer id)  {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
