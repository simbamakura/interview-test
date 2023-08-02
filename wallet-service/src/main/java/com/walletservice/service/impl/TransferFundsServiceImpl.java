package com.walletservice.service.impl;

import com.walletservice.data.entity.Account;
import com.walletservice.data.entity.AccountTransaction;
import com.walletservice.data.entity.TransactionType;
import com.walletservice.data.repo.AccountRepo;
import com.walletservice.data.repo.AccountTransactionRepo;
import com.walletservice.exception.ServiceException;
import com.walletservice.service.api.TransferFundsServiceApi;
import com.walletservice.service.model.TransferFundsRequest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransferFundsServiceImpl implements TransferFundsServiceApi {
    private final AccountRepo accountRepo;
    private final AccountTransactionRepo accountTransactionRepo;

    public TransferFundsServiceImpl(AccountRepo accountRepo, AccountTransactionRepo accountTransactionRepo) {
        this.accountRepo = accountRepo;
        this.accountTransactionRepo = accountTransactionRepo;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AccountTransaction transferFunds(TransferFundsRequest request) {
        Account sourceAccount = accountRepo.findByWalletId(request.getSourceWalletId())
                .orElseThrow(() -> new ServiceException("Source account not found"));
        Account destinationAccount = accountRepo.findByWalletId(request.getDestinationWalletId())
                .orElseThrow(() -> new ServiceException("Destination account not found"));


        // Calculate the timestamp 5 minutes ago
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -5);
        Date fiveMinutesAgo = cal.getTime();

        // Check if a similar transaction within the last 5 minutes already exists in the database
        List<AccountTransaction> existingTransactions = accountTransactionRepo.findBySimilarTransactionWithinTime(
                request.getSourceWalletId(), request.getDestinationWalletId(), request.getAmount(), fiveMinutesAgo);

        if (existingTransactions != null && !existingTransactions.isEmpty()) {
            throw new ServiceException("Duplicate transaction detected within the last 5 minutes.");
        }

        double amount = request.getAmount();
        if (sourceAccount.getBalance() < amount) {
            throw new ServiceException("Insufficient balance in the source account");
        }
        String transactionReference = UUID.randomUUID().toString();
        double sourceBalanceBeforeTransaction = sourceAccount.getBalance();
        double destinationBalanceBeforeTransaction = destinationAccount.getBalance();

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        AccountTransaction outgoingTransaction = createTransaction(sourceAccount,
                destinationAccount, amount, TransactionType.DEBIT);
        outgoingTransaction.setAccountHolder(sourceAccount);
        outgoingTransaction.setBalanceBeforeTransaction(sourceBalanceBeforeTransaction);
        outgoingTransaction.setBalanceAfterTransaction(sourceAccount.getBalance());
        outgoingTransaction.setTransactionReference(transactionReference);

        AccountTransaction incomingTransaction = createTransaction(sourceAccount, destinationAccount,
                amount, TransactionType.CREDIT);
        incomingTransaction.setAccountHolder(destinationAccount);
        incomingTransaction.setBalanceBeforeTransaction(destinationBalanceBeforeTransaction);
        incomingTransaction.setBalanceAfterTransaction(destinationAccount.getBalance());
        incomingTransaction.setTransactionReference(transactionReference);

        accountRepo.save(sourceAccount);
        accountRepo.save(destinationAccount);
        accountTransactionRepo.save(incomingTransaction);
        return accountTransactionRepo.save(outgoingTransaction);
    }

    private AccountTransaction createTransaction(Account sourceAccount,
                                                 Account destinationAccount,
                                                 double amount, TransactionType transactionType) {
        AccountTransaction transaction = new AccountTransaction();
        transaction.setAmount(amount);
        transaction.setCharges(0.0);
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setTransactionType(transactionType);
        return transaction;
    }
}
