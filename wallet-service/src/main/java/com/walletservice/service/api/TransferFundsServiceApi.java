package com.walletservice.service.api;

import com.walletservice.data.entity.AccountTransaction;
import com.walletservice.service.model.TransferFundsRequest;

public interface TransferFundsServiceApi {
    AccountTransaction transferFunds(TransferFundsRequest request);
}
