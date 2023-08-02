package com.walletservice.config.processor;

import com.walletservice.config.data.DataConfig;
import com.walletservice.config.service.ServiceConfig;
import com.walletservice.processor.api.BalanceProcessorApi;
import com.walletservice.processor.api.TransactionHistoryProcessorApi;
import com.walletservice.processor.api.TransferFundsProcessorApi;
import com.walletservice.processor.impl.BalanceProcessorImpl;
import com.walletservice.processor.impl.TransactionHistoryProcessorImpl;
import com.walletservice.processor.impl.TransferFundsProcessorImpl;
import com.walletservice.processor.mapper.api.MapperApi;
import com.walletservice.processor.mapper.impl.MapperImpl;
import com.walletservice.service.api.BalanceServiceApi;
import com.walletservice.service.api.TransactionHistoryServiceApi;
import com.walletservice.service.api.TransferFundsServiceApi;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({ServiceConfig.class})
@Configuration
public class ProcessorConfig {

    @Bean
    public MapperApi mapperApi() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        return new MapperImpl(dozerBeanMapper);
    }

    @Bean
    public BalanceProcessorApi balanceProcessorApi(final MapperApi mapperApi,
                                                   final BalanceServiceApi balanceServiceApi) {
        return new BalanceProcessorImpl(mapperApi, balanceServiceApi);
    }

    @Bean
    public TransferFundsProcessorApi transferFundsProcessorApi(final MapperApi mapperApi,
                                                               final TransferFundsServiceApi transferFundsServiceApi) {
        return new TransferFundsProcessorImpl(mapperApi, transferFundsServiceApi);
    }

    @Bean
    public TransactionHistoryProcessorApi
    transactionHistoryProcessorApi(final MapperApi mapperApi,
                                   final TransactionHistoryServiceApi transactionHistoryServiceApi) {
        return new TransactionHistoryProcessorImpl(mapperApi, transactionHistoryServiceApi);
    }
}
