package com.bank.account.service;


import com.bank.SharedDTO.Request.AccountRequest;
import com.bank.SharedDTO.Response.AccountResponse;

public interface AccountService {
    AccountResponse createAccount(AccountRequest accountRequest);

    AccountResponse getAccountById(Long accountId);

    AccountResponse updateAccount(Long accountId, AccountRequest accountRequest);

    Long balanceEnquiry(Long accountId);
}
