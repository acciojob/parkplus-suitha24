package com.driver.exception;

public class PaymentModeNotDetected extends RuntimeException{
    public PaymentModeNotDetected(String message){
        super(message);
    }
}
