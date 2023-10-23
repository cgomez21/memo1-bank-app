package com.aninfo.service;

import com.aninfo.exceptions.DepositZeroSumException;
import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.model.Account;
import com.aninfo.model.Deposit;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepositService extends TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Deposit createDeposit(Double amount, Account account) {

        if(amount < 0) {
            throw new DepositNegativeSumException("Amount to deposit cannot be negative");
        }

        if(amount == 0) {
            throw new DepositZeroSumException("Amount to deposit cannot be zero");
        }

        double extra_promo = amount/10;

        if(amount >= 2000) {
            if(extra_promo <= 500) {
                amount += extra_promo;
            } else {
                amount += 500;
            }
        }

        Deposit newDeposit = new Deposit(amount, account);
        transactionRepository.save(newDeposit);

        return newDeposit;
    }

}