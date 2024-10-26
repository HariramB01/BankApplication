package com.bank.SharedDTO.Response;

import com.bank.SharedDTO.status.TRANSACTION_STATUS;
import com.bank.SharedDTO.status.TRANSACTION_TYPE;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponse {

    private Long transactionId;
    private Long accountId;
    private Long userId;
    private TRANSACTION_TYPE transactionType;
    private double amount;
    private LocalDateTime transactionDate;
    private TRANSACTION_STATUS status;


}
