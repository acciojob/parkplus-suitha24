package com.driver.exception;

public class CannotMakeReservation extends RuntimeException{
    public CannotMakeReservation(String message){
        super(message);
    }
}
