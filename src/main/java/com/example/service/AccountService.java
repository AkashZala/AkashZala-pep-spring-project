package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.AccountAlreadyExistsException;
import com.example.exception.InvalidCredentialsExpection;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account register (Account newUser) {
        Account check = accountRepository.findAccountByUsername(newUser.getUsername());
        if(check == null && newUser.getUsername().length() > 0 && newUser.getPassword().length() >= 4) {
            return accountRepository.save(newUser);
        } 
        if (check != null) {
            throw new AccountAlreadyExistsException("Account with username already exists!");
        }
        return null;
    }

    public Account login (Account loginUser) {
        Account check = accountRepository.findAccountByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        if (check != null) {
          return check;  
        } else {
            throw new InvalidCredentialsExpection("Username and Password Combination do not match and existing users!");
        }
    }
        
}
