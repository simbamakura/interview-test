package com.walletservice.processor.api;

import com.walletservice.dto.AccountTransactionDto;
import com.walletservice.dto.ResponseDto;
import com.walletservice.dto.TransferFundsRequestDto;

public interface TransferFundsProcessorApi {
    ResponseDto<AccountTransactionDto> transferFunds(TransferFundsRequestDto requestDto);

}
