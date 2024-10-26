package com.bank.account.controller;


import com.bank.SharedDTO.Request.AccountRequest;
import com.bank.SharedDTO.Response.AccountResponse;
import com.bank.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest){
        AccountResponse accountResponse = accountService.createAccount(accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse);
    }

    @GetMapping("/getAccount/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId) {
        AccountResponse accountResponse = accountService.getAccountById(accountId);
        return ResponseEntity.ok(accountResponse);
    }

    @PutMapping("/updateAccount/{accountId}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long accountId, @RequestBody AccountRequest accountRequest) {
        AccountResponse updatedAccount = accountService.updateAccount(accountId, accountRequest);
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/balanceEnquiry/{id}")
    public ResponseEntity<Long> balanceEnquiry(@PathVariable Long id){
        return ResponseEntity.ok(accountService.balanceEnquiry(id));
    }

}
