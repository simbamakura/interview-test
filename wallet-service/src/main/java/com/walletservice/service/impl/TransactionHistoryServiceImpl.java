package com.walletservice.service.impl;

import com.walletservice.data.entity.AccountTransaction;
import com.walletservice.data.repo.AccountTransactionRepo;
import com.walletservice.service.api.TransactionHistoryServiceApi;
import com.walletservice.service.model.TransactionHistoryRequest;

import java.util.List;

public class TransactionHistoryServiceImpl implements TransactionHistoryServiceApi {
    private final AccountTransactionRepo accountTransactionRepo;

    public TransactionHistoryServiceImpl(AccountTransactionRepo accountTransactionRepo) {
        this.accountTransactionRepo = accountTransactionRepo;
    }

    @Override
    public List<AccountTransaction> queryTransactions(TransactionHistoryRequest request) {
        return accountTransactionRepo.queryTransactions(request.getAccountId(),
                request.getEndDate(),request.getStartDate());
    }
}
