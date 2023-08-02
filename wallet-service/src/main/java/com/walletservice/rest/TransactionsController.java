package com.walletservice.rest;

import com.walletservice.annotations.logger.Logger;
import com.walletservice.dto.AccountTransactionDto;
import com.walletservice.dto.ResponseDto;
import com.walletservice.dto.TransactionHistoryRequestDto;
import com.walletservice.processor.api.TransactionHistoryProcessorApi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Logger
@RestController()
@RequestMapping("transactions")
public class TransactionsController {

    private final TransactionHistoryProcessorApi transactionHistoryProcessorApi;

    public TransactionsController(TransactionHistoryProcessorApi transactionHistoryProcessorApi) {
        this.transactionHistoryProcessorApi = transactionHistoryProcessorApi;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDto<List<AccountTransactionDto>> queryTransactions(@RequestParam(value = "accountId")
                                                                         String accountId,
                                                                         @RequestParam(value = "startDate")
                                                                         String startDateStr,
                                                                         @RequestParam(value = "endDate")
                                                                         String endDateStr) throws ParseException {
        TransactionHistoryRequestDto request = new TransactionHistoryRequestDto();
        request.setAccountId(Long.parseLong(accountId));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        request.setStartDate(format.parse(startDateStr));
        request.setEndDate(format.parse(endDateStr));
        return transactionHistoryProcessorApi.queryTransactions(request);
    }
}
