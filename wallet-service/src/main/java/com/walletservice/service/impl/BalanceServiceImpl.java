package com.walletservice.service.impl;

import com.walletservice.data.entity.Account;
import com.walletservice.data.repo.AccountRepo;
import com.walletservice.exception.ServiceException;
import com.walletservice.service.api.BalanceServiceApi;

import java.util.List;

public class BalanceServiceImpl implements BalanceServiceApi {

    private final AccountRepo accountRepo;

    public BalanceServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public List<Account> getUserAccounts(String userId) {
        return accountRepo.findByUserId(userId);
    }

    @Override
    public Account getUserAccount(String walletId) {
        return accountRepo.findByWalletId(walletId).orElseThrow(() -> new ServiceException(walletId + " account not found"));
    }
}
