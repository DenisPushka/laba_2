package com.Errors;

public class InvalidBookPriceException extends  IllegalAccessException{

    public InvalidBookPriceException (String message){
        super(message);
    }
}
