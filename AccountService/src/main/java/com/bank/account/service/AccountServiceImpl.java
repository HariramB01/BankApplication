package com.bank.account.service;

import com.bank.SharedDTO.Response.UserResponse;
import com.bank.account.client.UserClient;
import com.bank.account.entity.Account;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.exception.UserNotFoundException;
import com.bank.account.mapper.AccountMapper;
import com.bank.account.repository.AccountRepository;
import com.bank.SharedDTO.Request.AccountRequest;
import com.bank.SharedDTO.Response.AccountResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    private UserClient userClient;

    public AccountServiceImpl(AccountRepository accountRepository, UserClient userClient){
        this.accountRepository = accountRepository;
        this.userClient = userClient;
    }


    @Override
    public AccountResponse createAccount(AccountRequest accountRequest) {
        UserResponse userResponse = userClient.getUserById(accountRequest.getUserId()).getBody();
        if(userResponse==null){
            throw new UserNotFoundException("User not found with ID: " + accountRequest.getUserId());
        }
        Account account = new Account();
        account.setAccountType(accountRequest.getAccountType());
        account.setBalance(accountRequest.getBalance());
        account.setUserId(userResponse.getId());
        account.setCurrency(accountRequest.getCurrency());
        account.setActive(accountRequest.isActive());
        accountRepository.save(account);
        return AccountMapper.convertAccountToAccountMapper(account);
    }

    public AccountResponse getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));
        return AccountMapper.convertAccountToAccountMapper(account);
    }



    @Override
    public AccountResponse updateAccount(Long accountId, AccountRequest accountRequest) {
        Optional<Account> existingAccountOpt = accountRepository.findById(accountId);
        if (existingAccountOpt.isPresent()) {
            Account existingAccount = existingAccountOpt.get();
            existingAccount.setAccountType(accountRequest.getAccountType());
            existingAccount.setBalance(accountRequest.getBalance());
            existingAccount.setCurrency(accountRequest.getCurrency());
            existingAccount.setActive(accountRequest.isActive());
            accountRepository.save(existingAccount);
            return AccountMapper.convertAccountToAccountMapper(existingAccount);
        } else {
            throw new AccountNotFoundException("Account with ID " + accountId + " not found");
        }
    }

    @Override
    public Long balanceEnquiry(Long id) {
        return accountRepository.getBalanceById(id);
    }

}
