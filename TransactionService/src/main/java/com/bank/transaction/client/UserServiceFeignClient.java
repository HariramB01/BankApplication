package com.bank.transaction.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {
}
