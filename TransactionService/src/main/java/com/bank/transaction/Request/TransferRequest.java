package com.bank.transaction.Request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferRequest {
    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
}
