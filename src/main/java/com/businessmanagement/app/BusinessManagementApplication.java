package com.businessmanagement.app;

import java.util.List;

import com.businessmanagement.exception.EmployeeNotFoundException;
import com.businessmanagement.model.Developer;
import com.businessmanagement.model.Employee;
import com.businessmanagement.model.Manager;
import com.businessmanagement.service.BusinessService;

public class BusinessManagementApplication {

    public static void main(String[] args)
            throws EmployeeNotFoundException {

        // Create service
        BusinessService service =
                new BusinessService();

        // ---------------------------------------------
        // ADD EMPLOYEES
        // ---------------------------------------------

        service.addEmployee(
                new Developer(
                        101,
                        "Rahul",
                        60000,
                        "IT",
                        "Java"));

        service.addEmployee(
                new Developer(
                        102,
                        "Priya",
                        50000,
                        "IT",
                        "Python"));

        service.addEmployee(
                new Manager(
                        103,
                        "Arun",
                        90000,
                        "HR",
                        10));

        // ---------------------------------------------
        // DISPLAY ALL EMPLOYEES
        // ---------------------------------------------

        System.out.println("===== ALL EMPLOYEES =====");

        for (Employee employee :
                service.getAllEmployees()) {

            System.out.println(employee);
        }

        // ---------------------------------------------
        // FIND EMPLOYEE
        // ---------------------------------------------

        System.out.println("\n===== FIND EMPLOYEE =====");

        Employee employee =
                service.findEmployee(101);

        System.out.println(employee);

        // ---------------------------------------------
        // DISPLAY DEPARTMENTS
        // ---------------------------------------------

        System.out.println("\n===== DEPARTMENTS =====");

        System.out.println(
                service.getDepartments());

        // ---------------------------------------------
        // FIND BY DEPARTMENT
        // ---------------------------------------------

        System.out.println(
                "\n===== IT EMPLOYEES =====");

        List<Employee> itEmployees =
                service.findByDepartment("IT");

        for (Employee e : itEmployees) {
            System.out.println(e.getName());
        }

        // ---------------------------------------------
        // FIND BY MINIMUM SALARY
        // ---------------------------------------------

        System.out.println(
                "\n===== SALARY >= 60000 =====");

        List<Employee> highSalaryEmployees =
                service.findByMinimumSalary(60000);

        for (Employee e : highSalaryEmployees) {
            System.out.println(
                    e.getName()
                    + " - "
                    + e.getSalary());
        }

        // ---------------------------------------------
        // SORT BY SALARY
        // ---------------------------------------------

        System.out.println(
                "\n===== SALARY ASCENDING =====");

        List<Employee> sortedEmployees =
                service.sortBySalaryAscending();

        for (Employee e : sortedEmployees) {
            System.out.println(
                    e.getName()
                    + " - "
                    + e.getSalary());
        }

        // ---------------------------------------------
        // UPDATE EMPLOYEE
        // ---------------------------------------------

        System.out.println(
                "\n===== UPDATE EMPLOYEE =====");

        service.updateEmployee(
                101,
                "Rahul Kumar",
                70000,
                "Development");

        Employee updatedEmployee =
                service.findEmployee(101);

        System.out.println(updatedEmployee);

        // ---------------------------------------------
        // REMOVE EMPLOYEE
        // ---------------------------------------------

        System.out.println(
                "\n===== REMOVE EMPLOYEE =====");

        service.removeEmployee(103);

        System.out.println(
                "Remaining employees: "
                + service.getAllEmployees().size());

        // ---------------------------------------------
        // FINAL DEPARTMENTS
        // ---------------------------------------------

        System.out.println(
                "\n===== FINAL DEPARTMENTS =====");

        System.out.println(
                service.getDepartments());

        System.out.println(
                "\n===== APPLICATION COMPLETED =====");
    }
}