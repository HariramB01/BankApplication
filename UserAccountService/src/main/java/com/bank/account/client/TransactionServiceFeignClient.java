package com.bank.account.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "transaction-service")
public interface TransactionServiceFeignClient {
}
