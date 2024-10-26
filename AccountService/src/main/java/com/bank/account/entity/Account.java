package com.bank.account.entity;


import com.bank.SharedDTO.status.ACCOUNT_TYPE;
import com.bank.SharedDTO.status.CURRENCY_TYPE;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ACCOUNT_TYPE accountType;

    @Column(nullable = false)
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CURRENCY_TYPE currency;

    @Column(nullable = false)
    private boolean isActive;

}
