package com.walletservice.processor.api;

import com.walletservice.dto.AccountDto;
import com.walletservice.dto.ResponseDto;

import java.util.List;

public interface BalanceProcessorApi {
    ResponseDto<List<AccountDto>> getUserAccountBalances( String userid);
    ResponseDto<AccountDto> getUserAccount( String walletId);
}
