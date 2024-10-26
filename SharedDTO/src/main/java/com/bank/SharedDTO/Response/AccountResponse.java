package com.bank.SharedDTO.Response;

import com.bank.SharedDTO.status.ACCOUNT_TYPE;
import com.bank.SharedDTO.status.CURRENCY_TYPE;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountResponse {
    private Long id;
    private Long userId;
    private ACCOUNT_TYPE accountType;
    private double balance;
    private CURRENCY_TYPE currency;
    private boolean isActive;
}
