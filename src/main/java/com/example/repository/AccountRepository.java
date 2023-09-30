package com.example.repository;


import com.example.domain.Account;
import com.example.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findFirstByOrderByIdDesc();

    Optional<Account> findByAccountNumber(String number);

    List<Account> findByAccountUser(AccountUser accountUser);
}
