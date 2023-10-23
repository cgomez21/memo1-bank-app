package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Deposit extends Transaction {

    public Deposit() {
        super();
    }

    @Override
    public void affectBalance() {
        Account account = this.getAccount();

        account.setBalance(account.getBalance() + this.getAmount());
    }

    public Deposit(Double amount, Account account) {
        super(amount, account);
        this.affectBalance();
    }
}
