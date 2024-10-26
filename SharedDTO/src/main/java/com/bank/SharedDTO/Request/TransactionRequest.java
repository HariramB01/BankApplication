package com.bank.SharedDTO.Request;

import com.bank.SharedDTO.status.TRANSACTION_STATUS;
import com.bank.SharedDTO.status.TRANSACTION_TYPE;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {

    @NotNull(message = "Account ID cannot be null")
    private Long accountId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Transaction type cannot be null")
    private TRANSACTION_TYPE transactionType;

    @DecimalMin(value = "0.01", message = "Transaction amount must be greater than 0")
    @Positive(message = "Transaction amount must be positive")
    private double amount;

    private LocalDateTime transactionDate;

    @NotNull(message = "Transaction status cannot be null")
    private TRANSACTION_STATUS status;
}
