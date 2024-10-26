package com.bank.account.client;


import com.bank.SharedDTO.Response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {
    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id);

}
