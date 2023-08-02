package com.walletservice.dto;

import com.walletservice.data.entity.Account;
import com.walletservice.data.entity.TransactionType;

import javax.persistence.*;

public class AccountTransactionDto extends BaseDto {
    private double amount;
    private double balanceBeforeTransaction;
    private double balanceAfterTransaction;
    private double charges;
    private String transactionReference;
    private String transactionType;
    private AccountDto sourceAccount;
    private AccountDto destinationAccount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalanceBeforeTransaction() {
        return balanceBeforeTransaction;
    }

    public void setBalanceBeforeTransaction(double balanceBeforeTransaction) {
        this.balanceBeforeTransaction = balanceBeforeTransaction;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public AccountDto getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(AccountDto sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public AccountDto getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(AccountDto destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
