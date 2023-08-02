package com.walletservice.processor.mapper.impl;

import com.walletservice.data.entity.Account;
import com.walletservice.data.entity.AccountTransaction;
import com.walletservice.dto.AccountDto;
import com.walletservice.dto.AccountTransactionDto;
import com.walletservice.processor.mapper.api.MapperApi;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperImpl implements MapperApi {
    private final DozerBeanMapper dozerBeanMapper;

    public MapperImpl(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    public List<AccountDto> mapAccounts(List<Account> accounts) {
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account : accounts) {
            AccountDto accountDto = mapTransactions(account);
            accountDtos.add(accountDto);
        }
        return accountDtos;
    }

    @Override
    public AccountDto mapTransactions(Account account) {
        return dozerBeanMapper.map(account, AccountDto.class);
    }

    @Override
    public <REQ, RES> RES map(REQ obj, Class<RES> classType) {
        return dozerBeanMapper.map(obj, classType);
    }

    @Override
    public List<AccountTransactionDto> mapTransactions(List<AccountTransaction> transactions) {
        List<AccountTransactionDto> transactionDtos = new ArrayList<>();
        for (AccountTransaction transaction : transactions) {
            transactionDtos.add(map(transaction, AccountTransactionDto.class));
        }
        return transactionDtos;
    }
}
