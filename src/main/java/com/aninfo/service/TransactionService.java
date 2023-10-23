package com.aninfo.service;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Collection;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Collection<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findTransactionById(id);
    }

    public Collection<Transaction> getTransactionsOfAccount(Long cbu) {
        return transactionRepository.findAllByAccount(accountRepository.findAccountByCbu(cbu));
    }

    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }


}