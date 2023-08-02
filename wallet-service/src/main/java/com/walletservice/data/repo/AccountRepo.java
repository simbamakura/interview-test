package com.walletservice.data.repo;

import com.walletservice.data.entity.Account;
import com.walletservice.data.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {

    List<Account> findByUserId(String userId);

    Optional<Account> findByWalletId(String walletId);

}
