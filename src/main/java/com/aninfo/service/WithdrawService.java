package com.aninfo.service;

import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.WithdrawNegativeSumException;
import com.aninfo.model.Account;
import com.aninfo.model.Withdraw;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Withdraw createWithdraw(Double amount, Account account) {

        if(account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        
        Withdraw newWithdraw = new Withdraw(amount, account);
        transactionRepository.save(newWithdraw);

        return newWithdraw;
    }

}