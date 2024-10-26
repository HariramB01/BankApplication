package com.bank.SharedDTO.Request;

import com.bank.SharedDTO.status.ACCOUNT_TYPE;
import com.bank.SharedDTO.status.CURRENCY_TYPE;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRequest {

    private Long id;

    @NotNull(message = "Account type cannot be null")
    private ACCOUNT_TYPE accountType;

    @NotNull(message = "User Id cannot be null")
    private Long userId;

    @DecimalMin(value = "0.0", inclusive = true, message = "Balance cannot be negative")
    @Positive(message = "Balance must be positive")
    private double balance;

    @NotNull(message = "Currency type cannot be null")
    private CURRENCY_TYPE currency;

    private boolean isActive;



}
