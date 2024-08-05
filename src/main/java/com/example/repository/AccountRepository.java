package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.username = ?1")
    Account findAccountByUsername(String username);

    @Query("SELECT a FROM Account a WHERE a.username = ?1 AND a.password = ?2")
    Account findAccountByUsernameAndPassword (String username, String password);
}
