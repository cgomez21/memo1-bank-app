package com.aninfo.model;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Optional;

@Entity
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Double amount;

    public Transaction() {
    }

    public Transaction(double amount, Account account) {
        this.amount = amount;
        this.account = account;
    }

    public Long getId() { return id; }

    public Double getAmount() { return amount; }

    public Account getAccount() { return account; }

    public abstract void affectBalance();
}
