package com.businessmanagement.exception;

public class EmployeeNotFoundException extends BusinessException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}