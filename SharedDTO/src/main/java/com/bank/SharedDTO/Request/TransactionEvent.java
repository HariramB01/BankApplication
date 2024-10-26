package com.bank.SharedDTO.Request;

import com.bank.SharedDTO.status.TRANSACTION_STATUS;
import com.bank.SharedDTO.status.TRANSACTION_TYPE;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEvent {

    private Long transactionId;
    private Long fromAccountId;
    private Long toAccountId;
    private Long userId;
    private TRANSACTION_TYPE transactionType;
    private double amount;
    private LocalDateTime transactionDate;
    private TRANSACTION_STATUS status;

}
