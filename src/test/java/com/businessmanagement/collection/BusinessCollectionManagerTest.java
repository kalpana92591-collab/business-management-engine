package com.businessmanagement.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessCollectionManagerTest {

    private BusinessCollectionManager manager;

    @BeforeEach
    void setUp() {
        manager = new BusinessCollectionManager();
    }

    // --------------------------------------------------
    // LIST TESTS
    // --------------------------------------------------

    @Test
    void shouldCreateAndUseGenericList() {

        List<String> names =
                manager.createList();

        manager.addToList(names, "Rahul");
        manager.addToList(names, "Priya");
        manager.addToList(names, "Arun");

        assertEquals(3, names.size());
        assertTrue(names.contains("Priya"));
    }

    @Test
    void shouldFilterListUsingLambda() {

        List<Integer> numbers =
                manager.createList();

        manager.addToList(numbers, 10);
        manager.addToList(numbers, 20);
        manager.addToList(numbers, 30);
        manager.addToList(numbers, 40);

        List<Integer> result =
                manager.filter(
                        numbers,
                        number -> number >= 30);

        assertEquals(2, result.size());
        assertTrue(result.contains(30));
        assertTrue(result.contains(40));
    }

    @Test
    void shouldFilterStringsUsingLambda() {

        List<String> names =
                manager.createList();

        manager.addToList(names, "Rahul");
        manager.addToList(names, "Priya");
        manager.addToList(names, "Arun");

        List<String> result =
                manager.filter(
                        names,
                        name -> name.startsWith("P"));

        assertEquals(1, result.size());
        assertEquals("Priya", result.get(0));
    }

    // --------------------------------------------------
    // SET TESTS
    // --------------------------------------------------

    @Test
    void shouldCreateAndUseGenericSet() {

        Set<String> departments =
                manager.createSet();

        manager.addToSet(departments, "IT");
        manager.addToSet(departments, "HR");
        manager.addToSet(departments, "IT");

        assertEquals(2, departments.size());
        assertTrue(departments.contains("IT"));
        assertTrue(departments.contains("HR"));
    }

    // --------------------------------------------------
    // MAP TESTS
    // --------------------------------------------------

    @Test
    void shouldCreateAndUseGenericMap() {

        Map<Integer, String> employees =
                manager.createMap();

        manager.addToMap(
                employees,
                101,
                "Rahul");

        manager.addToMap(
                employees,
                102,
                "Priya");

        assertEquals(2, employees.size());
        assertEquals("Rahul", employees.get(101));
        assertEquals("Priya", employees.get(102));
    }

    // --------------------------------------------------
    // COLLECTION SIZE TEST
    // --------------------------------------------------

    @Test
    void shouldReturnCorrectCollectionSizes() {

        List<String> names =
                manager.createList();

        Set<String> departments =
                manager.createSet();

        Map<Integer, String> employees =
                manager.createMap();

        manager.addToList(names, "Rahul");
        manager.addToList(names, "Priya");

        manager.addToSet(departments, "IT");
        manager.addToSet(departments, "HR");

        manager.addToMap(
                employees,
                101,
                "Rahul");

        manager.addToMap(
                employees,
                102,
                "Priya");

        assertEquals(
                2,
                manager.getListSize(names));

        assertEquals(
                2,
                manager.getSetSize(departments));

        assertEquals(
                2,
                manager.getMapSize(employees));
    }

    // --------------------------------------------------
    // INVALID LIST TESTS
    // --------------------------------------------------

    @Test
    void shouldRejectNullList() {

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.addToList(
                        null,
                        "Rahul"));
    }

    @Test
    void shouldRejectNullListItem() {

        List<String> names =
                manager.createList();

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.addToList(
                        names,
                        null));
    }

    // --------------------------------------------------
    // INVALID SET TESTS
    // --------------------------------------------------

    @Test
    void shouldRejectNullSet() {

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.addToSet(
                        null,
                        "IT"));
    }

    @Test
    void shouldRejectNullSetItem() {

        Set<String> departments =
                manager.createSet();

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.addToSet(
                        departments,
                        null));
    }

    // --------------------------------------------------
    // INVALID MAP TESTS
    // --------------------------------------------------

    @Test
    void shouldRejectNullMap() {

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.addToMap(
                        null,
                        101,
                        "Rahul"));
    }

    @Test
    void shouldRejectNullMapKey() {

        Map<Integer, String> employees =
                manager.createMap();

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.addToMap(
                        employees,
                        null,
                        "Rahul"));
    }

    @Test
    void shouldRejectNullMapValue() {

        Map<Integer, String> employees =
                manager.createMap();

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.addToMap(
                        employees,
                        101,
                        null));
    }

    // --------------------------------------------------
    // INVALID FILTER TESTS
    // --------------------------------------------------

    @Test
    void shouldRejectNullFilterList() {

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.filter(
                        null,
                        value -> true));
    }

    @Test
    void shouldRejectNullFilterCondition() {

        List<String> names =
                manager.createList();

        names.add("Rahul");

        assertThrows(
                IllegalArgumentException.class,
                () -> manager.filter(
                        names,
                        null));
    }
}