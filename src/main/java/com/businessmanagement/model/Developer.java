package com.businessmanagement.model;

public class Developer extends Employee {

    private String programmingLanguage;

    public Developer(int employeeId, String name, double salary,
                     String department, String programmingLanguage) {
        super(employeeId, name, salary, department);
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public String getRole() {
        return "Developer";
    }

    @Override
    public double calculateBonus() {
        return getSalary() * 0.10;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", programmingLanguage='" + programmingLanguage + '\'';
    }
}