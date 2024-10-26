package com.bank.transaction.Mapper;

import com.bank.SharedDTO.Request.AccountRequest;
import com.bank.SharedDTO.Request.TransactionEvent;
import com.bank.SharedDTO.Response.AccountResponse;
import com.bank.SharedDTO.status.TRANSACTION_STATUS;
import com.bank.SharedDTO.status.TRANSACTION_TYPE;
import com.bank.transaction.Entity.Transaction;

import java.time.LocalDateTime;

public class Mapper {
    public static AccountRequest convertAccountResponseToRequest(AccountResponse fromAccount) {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setId(fromAccount.getId());
        accountRequest.setUserId(fromAccount.getUserId());
        accountRequest.setAccountType(fromAccount.getAccountType());
        accountRequest.setCurrency(fromAccount.getCurrency());
        accountRequest.setBalance(fromAccount.getBalance());
        accountRequest.setActive(fromAccount.isActive());
        return accountRequest;
    }

    public static TransactionEvent convertTransactionToTransactionEvent(Transaction transfer) {
        TransactionEvent transactionEvent = new TransactionEvent();
        transactionEvent.setTransactionType(transfer.getTransactionType());
        transactionEvent.setAmount(transfer.getAmount());
        transactionEvent.setFromAccountId(transfer.getFromAccountId());
        transactionEvent.setToAccountId(transfer.getToAccountId());
        transactionEvent.setUserId(transfer.getUserId());
        transactionEvent.setTransactionDate(transfer.getTransactionDate());
        transactionEvent.setStatus(transfer.getStatus());
        return transactionEvent;
    }
}
