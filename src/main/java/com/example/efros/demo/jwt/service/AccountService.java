package com.example.efros.demo.jwt.service;

import com.example.efros.demo.jwt.domens.Account;
import com.example.efros.demo.jwt.domens.Industry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AccountService {
    private List<Account> accountList;

    public AccountService(){
        accountList=new ArrayList<>();
        //Account account=new Account(1L,"A","B", Industry.A,"C","D");
        accountList.add(new Account(1,"A","B", Industry.A,"C","D",null));

    }

    public List<Account> findAll(){
        return this.accountList;
    }

    public Account findById(Integer id){
         return accountList.get(Math.toIntExact(id));
    }

    public Account save(Account account){
        accountList.add(account);
        return account;
    }

   public Account findByUsername(String name){
        for (Account account:accountList){
            if(account.getUsername()==name){
                return account;
            }
        }
        return null;
   }
    public void delete(Integer id) {
        accountList.remove(id);
    }

    public boolean existsByUsername(String username) {
        for (Account account:accountList){
            if(account.getUsername()==username){
                return true;
            }
        }
        return false;
    }
}
