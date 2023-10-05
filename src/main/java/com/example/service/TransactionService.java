package com.example.service;

import com.example.domain.Account;
import com.example.domain.AccountUser;
import com.example.dto.TransactionDto;
import com.example.exception.AccountException;
import com.example.repository.AccountRepository;
import com.example.repository.AccountUserRepository;
import com.example.repository.TransactionRepository;
import com.example.type.AccountStatus;
import com.example.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountUserRepository accountUserRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public TransactionDto useBalance(Long userId, String accountNumber, Long amount){

        AccountUser user = accountUserRepository.findById(userId)
                .orElseThrow(() -> new AccountException(ErrorCode.USER_NOT_FOUND));

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException(ErrorCode.USER_NOT_FOUND));
        validateUseBalance(user, account, amount);
    }

    private void validateUseBalance(AccountUser user, Account account, Long amount){
        if(!Objects.equals(user.getId(), account.getAccountUser().getId())){
           throw new AccountException(ErrorCode.USER_ACCOUNT_UN_MATCH);
        }
        if(account.getAccountStatus() != AccountStatus.IN_USE){
            throw new AccountException(ErrorCode.ACCOUNT_ALREADY_UNREGISTERED);
        }
        if(account.getBalance() < amount){
            throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);
        }
    }
}
