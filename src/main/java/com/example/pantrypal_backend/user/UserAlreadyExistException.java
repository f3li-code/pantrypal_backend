package com.example.pantrypal_backend.user;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
