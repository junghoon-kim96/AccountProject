package com.example.controller;


import com.example.domain.Account;
import com.example.service.AccountService;
import com.example.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final RedisTestService redisTestService;

    @GetMapping("/get-lock")
    public String getLock() {
        return redisTestService.getLock();
    }

    @GetMapping("/create-account")
    public String createAccount() {
        accountService.createAccount();
        return "success";
    }

    @GetMapping("/account/{id}")
    public Account getAccount(
            @PathVariable Long id){
        return accountService.getAccount(id);
    }
}
