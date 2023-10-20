package com.naukma.introductionspringproject.exception;

public class UniqueEmailException extends RuntimeException {
    public UniqueEmailException(String message) {
        super(message);
    }
}