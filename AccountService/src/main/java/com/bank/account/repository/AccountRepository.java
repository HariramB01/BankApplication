package com.bank.account.repository;

import com.bank.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a.balance FROM Account a WHERE a.id = :id")
    Long getBalanceById(@Param("id") Long id);
}
