package com.bank.transaction.Repository;

import com.bank.transaction.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transaction WHERE fromAccountId = :accountId OR toAccountId = :accountId ORDER BY transaction_date DESC LIMIT 10",
            nativeQuery = true)
    List<Transaction> getLast10TransactionsByAccountId(@Param("accountId") Long accountId);
}
