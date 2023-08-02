package com.walletservice.dto;

public class TransferFundsRequestDto {
    private String sourceWalletId;
    private String destinationWalletId;
    private String amount;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
