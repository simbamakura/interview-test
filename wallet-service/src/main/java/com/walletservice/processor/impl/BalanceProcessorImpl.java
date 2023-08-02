package com.walletservice.processor.impl;

import com.walletservice.data.entity.Account;
import com.walletservice.dto.AccountDto;
import com.walletservice.dto.ResponseDto;
import com.walletservice.processor.api.BalanceProcessorApi;
import com.walletservice.processor.mapper.api.MapperApi;
import com.walletservice.service.api.BalanceServiceApi;

import java.util.List;

public class BalanceProcessorImpl implements BalanceProcessorApi {

    private final MapperApi mapperApi;
    private final BalanceServiceApi balanceServiceApi;

    public BalanceProcessorImpl(MapperApi mapperApi, BalanceServiceApi balanceServiceApi) {
        this.mapperApi = mapperApi;
        this.balanceServiceApi = balanceServiceApi;
    }

    @Override
    public ResponseDto<List<AccountDto>> getUserAccountBalances(String userid) {
        ResponseDto<List<AccountDto>> responseDto = new ResponseDto<>();
        responseDto.setSuccess(true);
        responseDto.setNarrative("Success");

        List<Account> userAccounts = balanceServiceApi.getUserAccounts(userid);
        if (userAccounts == null || userAccounts.isEmpty()) {
            responseDto.setSuccess(false);
            responseDto.setNarrative("No Accounts found");
        } else {
            responseDto.setData(mapperApi.mapAccounts(userAccounts));
        }
        return responseDto;
    }

    @Override
    public ResponseDto<AccountDto> getUserAccount(String walletId) {
        ResponseDto<AccountDto> responseDto = new ResponseDto<>();
        responseDto.setSuccess(true);
        responseDto.setNarrative("Success");
        Account account = balanceServiceApi.getUserAccount(walletId);
        AccountDto accountDto = mapperApi.mapTransactions(account);
        responseDto.setData(accountDto);
        return responseDto;
    }
}
