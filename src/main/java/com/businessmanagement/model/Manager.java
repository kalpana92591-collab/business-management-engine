package com.businessmanagement.model;

public class Manager extends Employee {

    private int teamSize;

    public Manager(int employeeId, String name, double salary,
                   String department, int teamSize) {
        super(employeeId, name, salary, department);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    @Override
    public String getRole() {
        return "Manager";
    }

    @Override
    public double calculateBonus() {
        return getSalary() * 0.20;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", teamSize=" + teamSize;
    }
}