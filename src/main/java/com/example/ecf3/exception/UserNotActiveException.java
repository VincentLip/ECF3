package com.example.ecf3.exception;

public class UserNotActiveException extends Exception{
    public UserNotActiveException() {
        super("User Not Active");
    }
}
