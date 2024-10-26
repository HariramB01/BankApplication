package com.bank.SharedDTO.dto;

import com.bank.SharedDTO.status.ACCOUNT_TYPE;
import com.bank.SharedDTO.status.CURRENCY_TYPE;
import jakarta.persistence.Embeddable;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class AccountDTO {

    private Long id;

    private UserDTO user;

    private ACCOUNT_TYPE accountType;

    private double balance;

    private CURRENCY_TYPE currency;

    private boolean isActive;

}
