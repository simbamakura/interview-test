package com.walletservice.service.api;

import com.walletservice.data.entity.Account;

import java.util.List;

public interface BalanceServiceApi {

    List<Account> getUserAccounts(String userId);

    Account getUserAccount(String walletId);
}
