package com.businessmanagement.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EmployeeInheritanceTest {

    @Test
    void shouldCreateDeveloper() {

        Developer developer = new Developer(
                101,
                "Rahul",
                60000,
                "IT",
                "Java");

        assertEquals(101, developer.getEmployeeId());
        assertEquals("Rahul", developer.getName());
        assertEquals("IT", developer.getDepartment());
        assertEquals("Java", developer.getProgrammingLanguage());

        assertEquals("Developer", developer.getRole());
    }

    @Test
    void shouldCalculateDeveloperBonus() {

        Developer developer = new Developer(
                101,
                "Rahul",
                60000,
                "IT",
                "Java");

        assertEquals(6000, developer.calculateBonus());
    }

    @Test
    void shouldCreateManager() {

        Manager manager = new Manager(
                201,
                "Priya",
                90000,
                "HR",
                10);

        assertEquals(201, manager.getEmployeeId());
        assertEquals("Priya", manager.getName());
        assertEquals("HR", manager.getDepartment());
        assertEquals(10, manager.getTeamSize());

        assertEquals("Manager", manager.getRole());
    }

    @Test
    void shouldCalculateManagerBonus() {

        Manager manager = new Manager(
                201,
                "Priya",
                90000,
                "HR",
                10);

        assertEquals(18000, manager.calculateBonus());
    }

    @Test
    void shouldUsePolymorphism() {

        Employee developer = new Developer(
                101,
                "Rahul",
                60000,
                "IT",
                "Java");

        Employee manager = new Manager(
                201,
                "Priya",
                90000,
                "HR",
                10);

        assertEquals("Developer", developer.getRole());
        assertEquals("Manager", manager.getRole());

        assertEquals(6000, developer.calculateBonus());
        assertEquals(18000, manager.calculateBonus());
    }

    @Test
    void shouldOverrideToString() {

        Developer developer = new Developer(
                101,
                "Rahul",
                60000,
                "IT",
                "Java");

        String result = developer.toString();

        assertTrue(result.contains("Rahul"));
        assertTrue(result.contains("Developer"));
        assertTrue(result.contains("Java"));
    }
}