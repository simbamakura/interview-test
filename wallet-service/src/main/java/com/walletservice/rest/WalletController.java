package com.walletservice.rest;

import com.walletservice.annotations.logger.Logger;
import com.walletservice.dto.AccountTransactionDto;
import com.walletservice.dto.ResponseDto;
import com.walletservice.dto.TransferFundsRequestDto;
import com.walletservice.processor.api.TransferFundsProcessorApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("wallet")
@Logger
public class WalletController {

    private final TransferFundsProcessorApi transferFundsProcessorApi;

    public WalletController(TransferFundsProcessorApi transferFundsProcessorApi) {
        this.transferFundsProcessorApi = transferFundsProcessorApi;
    }

    @PostMapping(path = "transfer",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<AccountTransactionDto> getUserBalances(@RequestBody TransferFundsRequestDto requestDto)
            throws Exception {
        try {
            Thread.sleep(3000);

        } catch (Exception ex) {

        }
        return transferFundsProcessorApi.transferFunds(requestDto);
    }

}
