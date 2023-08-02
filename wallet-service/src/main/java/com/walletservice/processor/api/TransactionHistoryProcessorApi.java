package com.walletservice.processor.api;

import com.walletservice.dto.AccountTransactionDto;
import com.walletservice.dto.ResponseDto;
import com.walletservice.dto.TransactionHistoryRequestDto;

import java.util.List;

public interface TransactionHistoryProcessorApi {
    ResponseDto<List<AccountTransactionDto>> queryTransactions(TransactionHistoryRequestDto request);
}
