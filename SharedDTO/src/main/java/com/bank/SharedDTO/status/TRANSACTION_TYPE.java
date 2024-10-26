package com.bank.SharedDTO.status;

public enum TRANSACTION_TYPE {
    DEPOSIT,
    WITHDRAW,
    FASTCASH,
    TRANSFER;

    public boolean requiresToAccountId() {
        return this == TRANSACTION_TYPE.TRANSFER;
    }
}
