package com.walletservice.service.api;

import com.walletservice.data.entity.AccountTransaction;
import com.walletservice.service.model.TransactionHistoryRequest;

import java.util.List;

public interface TransactionHistoryServiceApi {

    List<AccountTransaction> queryTransactions(TransactionHistoryRequest request);
}
