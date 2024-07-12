package com.assessment.library.exception;

public class BorrowerAlreadyExistException extends  RuntimeException{
    public BorrowerAlreadyExistException(String message){
        super(message);
    }
}
