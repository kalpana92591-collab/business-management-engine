package com.businessmanagement.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.businessmanagement.service.BusinessService;

class ExceptionTest {

    // -------------------------------------------------
    // BUSINESS EXCEPTION
    // -------------------------------------------------

    @Test
    void shouldCreateBusinessException() {

        BusinessException exception =
                new BusinessException(
                        "Business error");

        assertEquals(
                "Business error",
                exception.getMessage());
    }

    // -------------------------------------------------
    // BUSINESS EXCEPTION WITH CAUSE
    // -------------------------------------------------

    @Test
    void shouldCreateBusinessExceptionWithCause() {

        Exception cause =
                new Exception(
                        "Original error");

        BusinessException exception =
                new BusinessException(
                        "Business error",
                        cause);

        assertEquals(
                "Business error",
                exception.getMessage());

        assertNotNull(
                exception.getCause());

        assertEquals(
                "Original error",
                exception.getCause()
                        .getMessage());
    }

    // -------------------------------------------------
    // INVALID BUSINESS DATA EXCEPTION
    // -------------------------------------------------

    @Test
    void shouldCreateInvalidBusinessDataException() {

        InvalidBusinessDataException exception =
                new InvalidBusinessDataException(
                        "Invalid employee data");

        assertEquals(
                "Invalid employee data",
                exception.getMessage());
    }

    // -------------------------------------------------
    // EMPLOYEE NOT FOUND EXCEPTION
    // -------------------------------------------------

    @Test
    void shouldCreateEmployeeNotFoundException() {

        EmployeeNotFoundException exception =
                new EmployeeNotFoundException(
                        "Employee not found");

        assertEquals(
                "Employee not found",
                exception.getMessage());
    }

    // -------------------------------------------------
    // INVALID DATA EXCEPTION TEST
    // -------------------------------------------------

    @Test
    void shouldThrowInvalidBusinessDataException() {

        BusinessService service =
                new BusinessService();

        assertThrows(
                InvalidBusinessDataException.class,
                () -> service.addEmployee(null));
    }

    // -------------------------------------------------
    // EMPLOYEE NOT FOUND EXCEPTION TEST
    // -------------------------------------------------

    @Test
    void shouldThrowEmployeeNotFoundException() {

        BusinessService service =
                new BusinessService();

        assertThrows(
                EmployeeNotFoundException.class,
                () -> service.findEmployee(999));
    }
}