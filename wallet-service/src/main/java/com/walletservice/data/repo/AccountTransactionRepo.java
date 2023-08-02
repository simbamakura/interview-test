package com.walletservice.data.repo;

import com.walletservice.data.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AccountTransactionRepo extends JpaRepository<AccountTransaction, Long> {

    // Custom query to find a similar transaction within the last 5 minutes
    @Query("SELECT at FROM AccountTransaction at WHERE at.sourceAccount.walletId = :sourceWalletId " +
            "AND at.destinationAccount.walletId = :destinationWalletId " +
            "AND at.amount = :amount " +
            "AND at.dateCreated >= :fiveMinutesAgo")
    List<AccountTransaction> findBySimilarTransactionWithinTime(@Param("sourceWalletId") String sourceWalletId,
                                                                @Param("destinationWalletId") String destinationWalletId,
                                                                @Param("amount") double amount,
                                                                @Param("fiveMinutesAgo") Date fiveMinutesAgo);

    @Query("SELECT at FROM AccountTransaction at WHERE at.accountHolder.id = :accountId " +
            "AND  (at.dateCreated >= :startDate AND at.dateCreated < :endDate)")
    List<AccountTransaction> queryTransactions(@Param("accountId") Long accountId,
                                               @Param("endDate") Date endDate,
                                               @Param("startDate") Date startDate);
}
