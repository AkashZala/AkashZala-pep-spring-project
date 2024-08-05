package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.AccountAlreadyExistsException;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account register (Account newUser) {
        Account check = accountRepository.findAccountByUsername(newUser.getUsername());
        System.out.println(check);
        if(check == null && newUser.getUsername().length() > 0 && newUser.getPassword().length() >= 4) {
            return accountRepository.save(newUser);
        } 
        if (check != null) {
            throw new AccountAlreadyExistsException("Account with username already exists!");
        }
        return null;
    }
        
}
