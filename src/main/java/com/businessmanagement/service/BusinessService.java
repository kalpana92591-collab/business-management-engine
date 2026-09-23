package com.businessmanagement.service;

import java.util.List;
import java.util.Set;

import com.businessmanagement.collection.BusinessCollectionManager;
import com.businessmanagement.exception.EmployeeNotFoundException;
import com.businessmanagement.model.Employee;

public class BusinessService {

    private final EmployeeService employeeService;
    private final BusinessCollectionManager collectionManager;

    // -------------------------------------------------
    // CONSTRUCTOR
    // -------------------------------------------------

    public BusinessService() {

        this.employeeService =
                new EmployeeService();

        this.collectionManager =
                new BusinessCollectionManager();
    }

    // -------------------------------------------------
    // ADD EMPLOYEE
    // -------------------------------------------------

    public void addEmployee(Employee employee) {

        employeeService.addEmployee(employee);
    }

    // -------------------------------------------------
    // GET ALL EMPLOYEES
    // -------------------------------------------------

    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    // -------------------------------------------------
    // FIND EMPLOYEE
    // -------------------------------------------------

    public Employee findEmployee(int employeeId)
            throws EmployeeNotFoundException {

        return employeeService.findEmployee(
                employeeId);
    }

    // -------------------------------------------------
    // FIND EMPLOYEE USING MAP
    // -------------------------------------------------

    public Employee findEmployeeFromMap(int employeeId)
            throws EmployeeNotFoundException {

        return employeeService.findEmployeeFromMap(
                employeeId);
    }

    // -------------------------------------------------
    // REMOVE EMPLOYEE
    // -------------------------------------------------

    public void removeEmployee(int employeeId)
            throws EmployeeNotFoundException {

        employeeService.removeEmployee(
                employeeId);
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

        employeeService.updateEmployee(
                employeeId,
                newName,
                newSalary,
                newDepartment);
    }

    // -------------------------------------------------
    // GET DEPARTMENTS
    // -------------------------------------------------

    public Set<String> getDepartments() {

        return employeeService.getDepartments();
    }

    // -------------------------------------------------
    // FIND BY DEPARTMENT
    // -------------------------------------------------

    public List<Employee> findByDepartment(
            String department) {

        return employeeService.findByDepartment(
                department);
    }

    // -------------------------------------------------
    // FIND BY MINIMUM SALARY
    // -------------------------------------------------

    public List<Employee> findByMinimumSalary(
            double minimumSalary) {

        return employeeService.findByMinimumSalary(
                minimumSalary);
    }

    // -------------------------------------------------
    // SORT BY SALARY ASCENDING
    // -------------------------------------------------

    public List<Employee> sortBySalaryAscending() {

        return employeeService.sortBySalaryAscending();
    }

    // -------------------------------------------------
    // SORT BY SALARY DESCENDING
    // -------------------------------------------------

    public List<Employee> sortBySalaryDescending() {

        return employeeService.sortBySalaryDescending();
    }

    // -------------------------------------------------
    // SORT BY NAME
    // -------------------------------------------------

    public List<Employee> sortByName() {

        return employeeService.sortByName();
    }

    // -------------------------------------------------
    // FIND BY ROLE
    // -------------------------------------------------

    public List<Employee> findByRole(String role) {

        return employeeService.findByRole(role);
    }

    // -------------------------------------------------
    // FILTER GENERIC LIST
    // -------------------------------------------------

    public <T> List<T> filterList(
            List<T> list,
            java.util.function.Predicate<T> condition) {

        return collectionManager.filter(
                list,
                condition);
    }

    // -------------------------------------------------
    // GET COLLECTION MANAGER
    // -------------------------------------------------

    public BusinessCollectionManager
            getCollectionManager() {

        return collectionManager;
    }
}