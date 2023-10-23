package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Withdraw extends Transaction {

    public Withdraw() {
        super();
    }

    @Override
    public void affectBalance() {
        Account account = this.getAccount();

        account.setBalance(account.getBalance() - this.getAmount());
    }

    public Withdraw(Double amount, Account account) {
        super(amount, account);
        this.affectBalance();
    }
}
