package com.walletservice.processor.mapper.api;

import com.walletservice.data.entity.Account;
import com.walletservice.data.entity.AccountTransaction;
import com.walletservice.dto.AccountDto;
import com.walletservice.dto.AccountTransactionDto;

import java.util.List;

public interface MapperApi {

    List<AccountDto> mapAccounts(List<Account> accounts);
    AccountDto mapTransactions(Account account);

    <REQ, RES> RES map(REQ obj, Class<RES> classType);

    List<AccountTransactionDto> mapTransactions(List<AccountTransaction> transactions);
}
