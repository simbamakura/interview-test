package com.walletservice.rest;

import com.walletservice.annotations.logger.Logger;
import com.walletservice.dto.AccountDto;
import com.walletservice.dto.ResponseDto;
import com.walletservice.processor.api.BalanceProcessorApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Logger
@RestController()
@RequestMapping("account-balance")
public class BalanceController {

    private final BalanceProcessorApi balanceProcessor;

    public BalanceController(BalanceProcessorApi balanceProcessor) {
        this.balanceProcessor = balanceProcessor;
    }

    @GetMapping(path = "{userId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<List<AccountDto>> getUserBalances(@PathVariable(value = "userId", required = true) String userId)
            throws Exception {
//        try {
//            Thread.sleep(3000);
//
//        } catch (Exception ex) {
//
//        }
        return balanceProcessor.getUserAccountBalances(userId);
    }

    @GetMapping(path = "/wallet/{walletId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<AccountDto> getByWalletId(@PathVariable(value = "walletId", required = true)
                                                 String walletId) {
//        try {
//            Thread.sleep(3000);
//
//        } catch (Exception ex) {
//
//        }
        return balanceProcessor.getUserAccount(walletId);
    }
}
