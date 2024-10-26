package com.bank.transaction.Service;


import com.bank.transaction.Entity.Transaction;
import com.bank.transaction.Request.DepositRequest;
import com.bank.transaction.Request.TransferRequest;
import com.bank.transaction.Request.WithdrawRequest;

import java.util.List;

public interface TransactionService {
    void transfer(TransferRequest transferRequest);

    void withdraw(WithdrawRequest withdrawRequest);

    void deposit(DepositRequest depositRequest);

    void fastCash(WithdrawRequest withdrawRequest);

    List<Transaction> getStatement(Long accountId);
}
