package com.walletservice.config.service;

import com.walletservice.config.data.DataConfig;
import com.walletservice.data.repo.AccountRepo;
import com.walletservice.data.repo.AccountTransactionRepo;
import com.walletservice.service.api.BalanceServiceApi;
import com.walletservice.service.api.TransactionHistoryServiceApi;
import com.walletservice.service.api.TransferFundsServiceApi;
import com.walletservice.service.impl.BalanceServiceImpl;
import com.walletservice.service.impl.TransactionHistoryServiceImpl;
import com.walletservice.service.impl.TransferFundsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({DataConfig.class})
@Configuration
public class ServiceConfig {

    @Bean
    public BalanceServiceApi balanceServiceApi(final AccountRepo accountRepo) {
        return new BalanceServiceImpl(accountRepo);
    }

    @Bean
    public TransferFundsServiceApi transferFundsServiceApi(final AccountRepo accountRepo,
                                                           final AccountTransactionRepo accountTransactionRepo) {
        return new TransferFundsServiceImpl(accountRepo, accountTransactionRepo);
    }

    @Bean
    public TransactionHistoryServiceApi transactionHistoryServiceApi(final AccountTransactionRepo accountTransactionRepo) {
        return new TransactionHistoryServiceImpl(accountTransactionRepo);
    }
}
