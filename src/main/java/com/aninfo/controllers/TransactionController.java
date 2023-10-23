package com.aninfo.controllers;

import com.aninfo.model.Deposit;
import com.aninfo.model.Transaction;
import com.aninfo.model.Withdraw;
import com.aninfo.service.AccountService;
import com.aninfo.service.DepositService;
import com.aninfo.service.TransactionService;
import com.aninfo.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class TransactionController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private DepositService depositService;

    @GetMapping("/transactions")
    public Collection<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);

        return ResponseEntity.of(transaction);
    }

    @DeleteMapping("/transactions/delete/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransactionById(id);
    }

    @GetMapping("/transactions/account/{cbu}")
    public Collection<Transaction> getTransactionsOfAccount(@PathVariable Long cbu) {
        return transactionService.getTransactionsOfAccount(cbu);
    }

    @PostMapping("/transactions/deposit/{cbu}")
    public Deposit depositAmount(@PathVariable Long cbu, @RequestBody Double amount) {
        return depositService.createDeposit(amount, accountService.findAccountByCbu(cbu));
    }

    @PostMapping("/transactions/withdraw/{cbu}")
    public Withdraw withdrawAmount(@PathVariable Long cbu, @RequestBody Double amount) {
        return withdrawService.createWithdraw(amount, accountService.findAccountByCbu(cbu));
    }
}
