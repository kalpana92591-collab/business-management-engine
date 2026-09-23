package com.businessmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.businessmanagement.exception.EmployeeNotFoundException;
import com.businessmanagement.exception.InvalidBusinessDataException;
import com.businessmanagement.model.Developer;
import com.businessmanagement.model.Employee;
import com.businessmanagement.model.Manager;

class BusinessServiceTest {

    private BusinessService businessService;

    // -------------------------------------------------
    // SETUP
    // -------------------------------------------------

    @BeforeEach
    void setUp() {

        businessService =
                new BusinessService();

        businessService.addEmployee(
                new Developer(
                        101,
                        "Rahul",
                        60000,
                        "IT",
                        "Java"));

        businessService.addEmployee(
                new Developer(
                        102,
                        "Priya",
                        50000,
                        "IT",
                        "Python"));

        businessService.addEmployee(
                new Manager(
                        103,
                        "Arun",
                        90000,
                        "HR",
                        10));
    }

    // -------------------------------------------------
    // ADD EMPLOYEE
    // -------------------------------------------------

    @Test
    void shouldAddEmployees() {

        List<Employee> employees =
                businessService.getAllEmployees();

        assertEquals(
                3,
                employees.size());
    }

    // -------------------------------------------------
    // FIND EMPLOYEE
    // -------------------------------------------------

    @Test
    void shouldFindEmployee()
            throws EmployeeNotFoundException {

        Employee employee =
                businessService.findEmployee(101);

        assertEquals(
                "Rahul",
                employee.getName());

        assertEquals(
                "IT",
                employee.getDepartment());
    }

    // -------------------------------------------------
    // FIND EMPLOYEE USING MAP
    // -------------------------------------------------

    @Test
    void shouldFindEmployeeUsingMap()
            throws EmployeeNotFoundException {

        Employee employee =
                businessService.findEmployeeFromMap(103);

        assertEquals(
                "Arun",
                employee.getName());

        assertEquals(
                "HR",
                employee.getDepartment());
    }

    // -------------------------------------------------
    // FIND BY DEPARTMENT
    // -------------------------------------------------

    @Test
    void shouldFindEmployeesByDepartment() {

        List<Employee> employees =
                businessService.findByDepartment("IT");

        assertEquals(
                2,
                employees.size());
    }

    // -------------------------------------------------
    // FIND BY MINIMUM SALARY
    // -------------------------------------------------

    @Test
    void shouldFindEmployeesByMinimumSalary() {

        List<Employee> employees =
                businessService.findByMinimumSalary(60000);

        assertEquals(
                2,
                employees.size());
    }

    // -------------------------------------------------
    // SORT BY SALARY ASCENDING
    // -------------------------------------------------

    @Test
    void shouldSortEmployeesBySalaryAscending() {

        List<Employee> employees =
                businessService.sortBySalaryAscending();

        assertEquals(
                "Priya",
                employees.get(0).getName());

        assertEquals(
                "Arun",
                employees.get(2).getName());
    }

    // -------------------------------------------------
    // SORT BY SALARY DESCENDING
    // -------------------------------------------------

    @Test
    void shouldSortEmployeesBySalaryDescending() {

        List<Employee> employees =
                businessService.sortBySalaryDescending();

        assertEquals(
                "Arun",
                employees.get(0).getName());

        assertEquals(
                "Priya",
                employees.get(2).getName());
    }

    // -------------------------------------------------
    // FIND BY ROLE
    // -------------------------------------------------

    @Test
    void shouldFindEmployeesByRole() {

        List<Employee> developers =
                businessService.findByRole(
                        "Developer");

        assertEquals(
                2,
                developers.size());
    }

    // -------------------------------------------------
    // UNIQUE DEPARTMENTS
    // -------------------------------------------------

    @Test
    void shouldReturnUniqueDepartments() {

        assertEquals(
                2,
                businessService.getDepartments()
                        .size());

        assertTrue(
                businessService.getDepartments()
                        .contains("IT"));

        assertTrue(
                businessService.getDepartments()
                        .contains("HR"));
    }

    // -------------------------------------------------
    // UNKNOWN EMPLOYEE
    // -------------------------------------------------

    @Test
    void shouldThrowExceptionForUnknownEmployee() {

        assertThrows(
                EmployeeNotFoundException.class,
                () -> businessService.findEmployee(999));
    }

    // -------------------------------------------------
    // NULL EMPLOYEE
    // -------------------------------------------------

    @Test
    void shouldRejectNullEmployee() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.addEmployee(null));
    }

    // -------------------------------------------------
    // DUPLICATE EMPLOYEE ID
    // -------------------------------------------------

    @Test
    void shouldRejectDuplicateEmployeeId() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.addEmployee(
                        new Developer(
                                101,
                                "Another Employee",
                                55000,
                                "IT",
                                "Java")));
    }

    // -------------------------------------------------
    // REMOVE EMPLOYEE
    // -------------------------------------------------

    @Test
    void shouldRemoveEmployeeAndUnusedDepartment()
            throws EmployeeNotFoundException {

        businessService.removeEmployee(103);

        assertEquals(
                2,
                businessService.getAllEmployees()
                        .size());

        assertTrue(
                !businessService.getDepartments()
                        .contains("HR"));
    }

    // -------------------------------------------------
    // UPDATE EMPLOYEE
    // -------------------------------------------------

    @Test
    void shouldUpdateEmployee()
            throws EmployeeNotFoundException {

        businessService.updateEmployee(
                101,
                "Rahul Kumar",
                70000,
                "Development");

        Employee employee =
                businessService.findEmployee(101);

        assertEquals(
                "Rahul Kumar",
                employee.getName());

        assertEquals(
                70000,
                employee.getSalary());

        assertEquals(
                "Development",
                employee.getDepartment());
    }

    // -------------------------------------------------
    // UPDATE DEPARTMENT
    // -------------------------------------------------

    @Test
    void shouldUpdateDepartmentCorrectly()
            throws EmployeeNotFoundException {

        businessService.updateEmployee(
                103,
                "Arun Kumar",
                95000,
                "Finance");

        assertTrue(
                businessService.getDepartments()
                        .contains("Finance"));

        assertTrue(
                !businessService.getDepartments()
                        .contains("HR"));
    }

    // -------------------------------------------------
    // INVALID UPDATED NAME
    // -------------------------------------------------

    @Test
    void shouldRejectBlankUpdatedName() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.updateEmployee(
                        101,
                        "",
                        70000,
                        "IT"));
    }

    // -------------------------------------------------
    // INVALID UPDATED SALARY
    // -------------------------------------------------

    @Test
    void shouldRejectInvalidUpdatedSalary() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.updateEmployee(
                        101,
                        "Rahul",
                        0,
                        "IT"));
    }

    // -------------------------------------------------
    // INVALID UPDATED DEPARTMENT
    // -------------------------------------------------

    @Test
    void shouldRejectBlankUpdatedDepartment() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.updateEmployee(
                        101,
                        "Rahul",
                        70000,
                        ""));
    }

    // -------------------------------------------------
    // UPDATE UNKNOWN EMPLOYEE
    // -------------------------------------------------

    @Test
    void shouldRejectUpdateForUnknownEmployee() {

        assertThrows(
                EmployeeNotFoundException.class,
                () -> businessService.updateEmployee(
                        999,
                        "Unknown",
                        70000,
                        "IT"));
    }

    // -------------------------------------------------
    // INVALID EMPLOYEE ID
    // -------------------------------------------------

    @Test
    void shouldRejectInvalidEmployeeId() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.addEmployee(
                        new Developer(
                                0,
                                "Test Employee",
                                50000,
                                "IT",
                                "Java")));
    }

    // -------------------------------------------------
    // BLANK EMPLOYEE NAME
    // -------------------------------------------------

    @Test
    void shouldRejectBlankEmployeeName() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.addEmployee(
                        new Developer(
                                104,
                                "",
                                50000,
                                "IT",
                                "Java")));
    }

    // -------------------------------------------------
    // INVALID SALARY
    // -------------------------------------------------

    @Test
    void shouldRejectInvalidSalary() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.addEmployee(
                        new Developer(
                                105,
                                "Test Employee",
                                0,
                                "IT",
                                "Java")));
    }

    // -------------------------------------------------
    // BLANK DEPARTMENT
    // -------------------------------------------------

    @Test
    void shouldRejectBlankDepartment() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.addEmployee(
                        new Developer(
                                106,
                                "Test Employee",
                                50000,
                                "",
                                "Java")));
    }

    // -------------------------------------------------
    // NEGATIVE MINIMUM SALARY
    // -------------------------------------------------

    @Test
    void shouldRejectNegativeMinimumSalary() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.findByMinimumSalary(
                        -1000));
    }

    // -------------------------------------------------
    // BLANK DEPARTMENT SEARCH
    // -------------------------------------------------

    @Test
    void shouldRejectBlankDepartmentSearch() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.findByDepartment(""));
    }

    // -------------------------------------------------
    // BLANK ROLE SEARCH
    // -------------------------------------------------

    @Test
    void shouldRejectBlankRoleSearch() {

        assertThrows(
                InvalidBusinessDataException.class,
                () -> businessService.findByRole(""));
    }
}