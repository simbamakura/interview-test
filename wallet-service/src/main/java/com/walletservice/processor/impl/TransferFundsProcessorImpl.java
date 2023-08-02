package com.walletservice.processor.impl;

import com.walletservice.data.entity.AccountTransaction;
import com.walletservice.dto.AccountTransactionDto;
import com.walletservice.dto.ResponseDto;
import com.walletservice.dto.TransferFundsRequestDto;
import com.walletservice.processor.api.TransferFundsProcessorApi;
import com.walletservice.processor.mapper.api.MapperApi;
import com.walletservice.service.api.TransferFundsServiceApi;
import com.walletservice.service.model.TransferFundsRequest;

public class TransferFundsProcessorImpl implements TransferFundsProcessorApi {

    private final MapperApi mapperApi;
    private final TransferFundsServiceApi transferFundsServiceApi;

    public TransferFundsProcessorImpl(MapperApi mapperApi, TransferFundsServiceApi transferFundsServiceApi) {
        this.mapperApi = mapperApi;
        this.transferFundsServiceApi = transferFundsServiceApi;
    }

    @Override
    public ResponseDto<AccountTransactionDto> transferFunds(TransferFundsRequestDto requestDto) {
        ResponseDto<AccountTransactionDto> responseDto = new ResponseDto<>();
        responseDto.setSuccess(true);
        responseDto.setNarrative("Success");
        TransferFundsRequest request = mapperApi.map(requestDto, TransferFundsRequest.class);
        AccountTransaction accountTransaction = transferFundsServiceApi.transferFunds(request);
        AccountTransactionDto transactionDto = mapperApi.map(accountTransaction, AccountTransactionDto.class);
        responseDto.setData(transactionDto);
        return responseDto;
    }
}
