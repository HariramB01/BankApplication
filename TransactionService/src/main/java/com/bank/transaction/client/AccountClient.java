package com.bank.transaction.client;

import com.bank.SharedDTO.Request.AccountRequest;
import com.bank.SharedDTO.Response.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountClient {
    @GetMapping("/api/account/getAccount/{accountId}")
    ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId);

    @PutMapping("/api/account/updateAccount/{accountId}")
    ResponseEntity<AccountResponse> updateAccount(@PathVariable Long accountId, @RequestBody AccountRequest accountRequest);
}
