package com.example.insuranceproject.exception;


public class MyNotFoundException extends RuntimeException{

    public MyNotFoundException() {
        super("Aranan şey bulunamadı!");
    }
}