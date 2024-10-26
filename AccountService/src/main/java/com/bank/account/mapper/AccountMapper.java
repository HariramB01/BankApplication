package com.bank.account.mapper;

import com.bank.account.entity.Account;
import com.bank.SharedDTO.Response.AccountResponse;

public class AccountMapper {
    public static AccountResponse convertAccountToAccountMapper(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setUserId(account.getUserId());
        accountResponse.setAccountType(account.getAccountType());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setAccountType(account.getAccountType());
        accountResponse.setCurrency(account.getCurrency());
        return accountResponse;
    }
}
