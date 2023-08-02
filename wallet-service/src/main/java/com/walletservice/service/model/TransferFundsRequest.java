package com.walletservice.service.model;

public class TransferFundsRequest {
    private String sourceWalletId;
    private String destinationWalletId;
    private double amount;

    public String getSourceWalletId() {
        return sourceWalletId;
    }

    public void setSourceWalletId(String sourceWalletId) {
        this.sourceWalletId = sourceWalletId;
    }

    public String getDestinationWalletId() {
        return destinationWalletId;
    }

    public void setDestinationWalletId(String destinationWalletId) {
        this.destinationWalletId = destinationWalletId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
