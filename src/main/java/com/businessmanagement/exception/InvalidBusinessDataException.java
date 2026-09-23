package com.businessmanagement.exception;

public class InvalidBusinessDataException extends RuntimeException {

    public InvalidBusinessDataException(String message) {
        super(message);
    }

    public InvalidBusinessDataException(String message, Throwable cause) {
        super(message, cause);
    }
}