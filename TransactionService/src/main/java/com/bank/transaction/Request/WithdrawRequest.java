package com.bank.transaction.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WithdrawRequest {
    private Long accountId;
    private double amount;
}
