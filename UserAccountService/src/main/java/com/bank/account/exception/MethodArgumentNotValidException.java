package com.bank.account.exception;

public class MethodArgumentNotValidException extends RuntimeException{

    public MethodArgumentNotValidException(String message){
        super(message);
    }

}
