package com.businessmanagement.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.businessmanagement.exception.EmployeeNotFoundException;
import com.businessmanagement.exception.InvalidBusinessDataException;
import com.businessmanagement.model.Employee;

public class EmployeeService {

    // List - stores all employees
    private final List<Employee> employees = new ArrayList<>();

    // Set - stores unique departments
    private final Set<String> departments = new HashSet<>();

    // Map - employee ID -> Employee
    private final Map<Integer, Employee> employeeMap = new HashMap<>();

    // -------------------------------------------------
    // ADD EMPLOYEE
    // -------------------------------------------------

    public void addEmployee(Employee employee) {

        if (employee == null) {
            throw new InvalidBusinessDataException(
                    "Employee cannot be null");
        }

        if (employee.getEmployeeId() <= 0) {
            throw new InvalidBusinessDataException(
                    "Employee ID must be greater than zero");
        }

        if (employee.getName() == null ||
                employee.getName().isBlank()) {
            throw new InvalidBusinessDataException(
                    "Employee name cannot be blank");
        }

        if (employee.getSalary() <= 0) {
            throw new InvalidBusinessDataException(
                    "Employee salary must be greater than zero");
        }

        if (employee.getDepartment() == null ||
                employee.getDepartment().isBlank()) {
            throw new InvalidBusinessDataException(
                    "Department cannot be blank");
        }

        // Check duplicate employee ID
        boolean duplicate = employees.stream()
                .anyMatch(e ->
                        e.getEmployeeId()
                                == employee.getEmployeeId());

        if (duplicate) {
            throw new InvalidBusinessDataException(
                    "Employee ID already exists: "
                            + employee.getEmployeeId());
        }

        // Add employee to List
        employees.add(employee);

        // Add department to Set
        departments.add(employee.getDepartment());

        // Add employee to Map
        employeeMap.put(
                employee.getEmployeeId(),
                employee);
    }

    // -------------------------------------------------
    // GET ALL EMPLOYEES
    // -------------------------------------------------

    public List<Employee> getAllEmployees() {
        return List.copyOf(employees);
    }

    // -------------------------------------------------
    // FIND EMPLOYEE USING LIST + STREAM
    // -------------------------------------------------

    public Employee findEmployee(int employeeId)
            throws EmployeeNotFoundException {

        return employees.stream()
                .filter(e ->
                        e.getEmployeeId()
                                == employeeId)
                .findFirst()
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found: "
                                        + employeeId));
    }

    // -------------------------------------------------
    // FIND EMPLOYEE USING MAP
    // -------------------------------------------------

    public Employee findEmployeeFromMap(int employeeId)
            throws EmployeeNotFoundException {

        Employee employee =
                employeeMap.get(employeeId);

        if (employee == null) {
            throw new EmployeeNotFoundException(
                    "Employee not found: "
                            + employeeId);
        }

        return employee;
    }

    // -------------------------------------------------
    // REMOVE EMPLOYEE
    // -------------------------------------------------

    public void removeEmployee(int employeeId)
            throws EmployeeNotFoundException {

        Employee employee =
                findEmployee(employeeId);

        // Remove employee from List
        employees.remove(employee);

        // Remove employee from Map
        employeeMap.remove(employeeId);

        // Check whether department is still used
        boolean departmentStillUsed =
                employees.stream()
                        .anyMatch(e ->
                                e.getDepartment()
                                        .equals(employee.getDepartment()));

        // Remove department if no employee uses it
        if (!departmentStillUsed) {
            departments.remove(
                    employee.getDepartment());
        }
    }

    // -------------------------------------------------
    // UPDATE EMPLOYEE
    // -------------------------------------------------

    public void updateEmployee(
            int employeeId,
            String newName,
            double newSalary,
            String newDepartment)
            throws EmployeeNotFoundException {

        // Find existing employee
        Employee employee =
                findEmployee(employeeId);

        // Validate name
        if (newName == null ||
                newName.isBlank()) {

            throw new InvalidBusinessDataException(
                    "Employee name cannot be blank");
        }

        // Validate salary
        if (newSalary <= 0) {

            throw new InvalidBusinessDataException(
                    "Employee salary must be greater than zero");
        }

        // Validate department
        if (newDepartment == null ||
                newDepartment.isBlank()) {

            throw new InvalidBusinessDataException(
                    "Department cannot be blank");
        }

        // Store old department
        String oldDepartment =
                employee.getDepartment();

        // Update employee details
        employee.setName(newName);
        employee.setSalary(newSalary);
        employee.setDepartment(newDepartment);

        // Add new department
        departments.add(newDepartment);

        // Check whether old department
        // is still used by another employee
        boolean oldDepartmentStillUsed =
                employees.stream()
                        .anyMatch(e ->
                                e.getDepartment()
                                        .equals(oldDepartment));

        // Remove old department if unused
        if (!oldDepartmentStillUsed) {
            departments.remove(oldDepartment);
        }

        // Update Map
        employeeMap.put(
                employeeId,
                employee);
    }

    // -------------------------------------------------
    // GET UNIQUE DEPARTMENTS
    // -------------------------------------------------

    public Set<String> getDepartments() {
        return Set.copyOf(departments);
    }

    // -------------------------------------------------
    // GET EMPLOYEE MAP
    // -------------------------------------------------

    public Map<Integer, Employee> getEmployeeMap() {
        return Map.copyOf(employeeMap);
    }

    // -------------------------------------------------
    // FIND BY DEPARTMENT
    // -------------------------------------------------

    public List<Employee> findByDepartment(
            String department) {

        if (department == null ||
                department.isBlank()) {

            throw new InvalidBusinessDataException(
                    "Department cannot be blank");
        }

        return employees.stream()
                .filter(e ->
                        e.getDepartment()
                                .equalsIgnoreCase(department))
                .toList();
    }

    // -------------------------------------------------
    // FIND BY MINIMUM SALARY
    // -------------------------------------------------

    public List<Employee> findByMinimumSalary(
            double minimumSalary) {

        if (minimumSalary < 0) {

            throw new InvalidBusinessDataException(
                    "Minimum salary cannot be negative");
        }

        return employees.stream()
                .filter(e ->
                        e.getSalary()
                                >= minimumSalary)
                .toList();
    }

    // -------------------------------------------------
    // SORT BY SALARY ASCENDING
    // -------------------------------------------------

    public List<Employee> sortBySalaryAscending() {

        return employees.stream()
                .sorted(
                        Comparator.comparingDouble(
                                Employee::getSalary))
                .toList();
    }

    // -------------------------------------------------
    // SORT BY SALARY DESCENDING
    // -------------------------------------------------

    public List<Employee> sortBySalaryDescending() {

        return employees.stream()
                .sorted(
                        Comparator.comparingDouble(
                                Employee::getSalary)
                                .reversed())
                .toList();
    }

    // -------------------------------------------------
    // SORT BY NAME
    // -------------------------------------------------

    public List<Employee> sortByName() {

        return employees.stream()
                .sorted(
                        Comparator.comparing(
                                Employee::getName))
                .toList();
    }

    // -------------------------------------------------
    // FIND BY ROLE
    // -------------------------------------------------

    public List<Employee> findByRole(
            String role) {

        if (role == null ||
                role.isBlank()) {

            throw new InvalidBusinessDataException(
                    "Role cannot be blank");
        }

        return employees.stream()
                .filter(e ->
                        e.getRole()
                                .equalsIgnoreCase(role))
                .toList();
    }
}