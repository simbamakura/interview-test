package com.walletservice.processor.impl;

import com.walletservice.data.entity.AccountTransaction;
import com.walletservice.dto.AccountTransactionDto;
import com.walletservice.dto.ResponseDto;
import com.walletservice.dto.TransactionHistoryRequestDto;
import com.walletservice.processor.api.TransactionHistoryProcessorApi;
import com.walletservice.processor.mapper.api.MapperApi;
import com.walletservice.service.api.TransactionHistoryServiceApi;
import com.walletservice.service.model.TransactionHistoryRequest;

import java.util.List;

public class TransactionHistoryProcessorImpl implements TransactionHistoryProcessorApi {
    private final MapperApi mapperApi;
    private final TransactionHistoryServiceApi transactionHistoryServiceApi;

    public TransactionHistoryProcessorImpl(MapperApi mapperApi, TransactionHistoryServiceApi transactionHistoryServiceApi) {
        this.mapperApi = mapperApi;
        this.transactionHistoryServiceApi = transactionHistoryServiceApi;
    }

    @Override
    public ResponseDto<List<AccountTransactionDto>> queryTransactions(TransactionHistoryRequestDto request) {
        ResponseDto<List<AccountTransactionDto>> responseDto = new ResponseDto<>();
        responseDto.setSuccess(true);
        responseDto.setNarrative("Success");
        TransactionHistoryRequest transactionHistoryRequest = mapperApi.map(request, TransactionHistoryRequest.class);
        List<AccountTransaction> accountTransactions = transactionHistoryServiceApi.queryTransactions(transactionHistoryRequest);
        List<AccountTransactionDto> transactionDtos = mapperApi.mapTransactions(accountTransactions);
        responseDto.setData(transactionDtos);
        return responseDto;
    }
}
