package com.walletservice.data.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Account extends BaseEntity {
    private String userId;

    private String walletId;
    private String accountName;
    private double balance;

    @OneToMany(mappedBy = "sourceAccount")
    private List<AccountTransaction> outgoingTransactions;

    @OneToMany(mappedBy = "destinationAccount")
    private List<AccountTransaction> incomingTransactions;

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public List<AccountTransaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }

    public void setOutgoingTransactions(List<AccountTransaction> outgoingTransactions) {
        this.outgoingTransactions = outgoingTransactions;
    }

    public List<AccountTransaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void setIncomingTransactions(List<AccountTransaction> incomingTransactions) {
        this.incomingTransactions = incomingTransactions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
