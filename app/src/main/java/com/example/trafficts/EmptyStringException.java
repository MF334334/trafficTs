package com.example.trafficts;

//自定义异常
public class EmptyStringException extends Exception {
    public EmptyStringException(String message) {
        super(message);
    }
}
