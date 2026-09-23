package com.businessmanagement.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class BusinessCollectionManager {

    // -----------------------------
    // GENERIC LIST METHODS
    // -----------------------------

    // Add an item to a List
    public <T> void addToList(List<T> list, T item) {

        if (list == null) {
            throw new IllegalArgumentException(
                    "List cannot be null");
        }

        if (item == null) {
            throw new IllegalArgumentException(
                    "Item cannot be null");
        }

        list.add(item);
    }

    // Create a new generic List
    public <T> List<T> createList() {
        return new ArrayList<>();
    }

    // -----------------------------
    // GENERIC SET METHODS
    // -----------------------------

    // Add an item to a Set
    public <T> void addToSet(Set<T> set, T item) {

        if (set == null) {
            throw new IllegalArgumentException(
                    "Set cannot be null");
        }

        if (item == null) {
            throw new IllegalArgumentException(
                    "Item cannot be null");
        }

        set.add(item);
    }

    // Create a new generic Set
    public <T> Set<T> createSet() {
        return new HashSet<>();
    }

    // -----------------------------
    // GENERIC MAP METHODS
    // -----------------------------

    // Add key-value pair to Map
    public <K, V> void addToMap(
            Map<K, V> map,
            K key,
            V value) {

        if (map == null) {
            throw new IllegalArgumentException(
                    "Map cannot be null");
        }

        if (key == null) {
            throw new IllegalArgumentException(
                    "Key cannot be null");
        }

        if (value == null) {
            throw new IllegalArgumentException(
                    "Value cannot be null");
        }

        map.put(key, value);
    }

    // Create a new generic Map
    public <K, V> Map<K, V> createMap() {
        return new HashMap<>();
    }

    // -----------------------------
    // GENERIC FILTER METHOD
    // -----------------------------

    // Filter any List using a condition
    public <T> List<T> filter(
            List<T> list,
            Predicate<T> condition) {

        if (list == null) {
            throw new IllegalArgumentException(
                    "List cannot be null");
        }

        if (condition == null) {
            throw new IllegalArgumentException(
                    "Condition cannot be null");
        }

        return list.stream()
                .filter(condition)
                .toList();
    }

    // -----------------------------
    // GENERIC SIZE METHODS
    // -----------------------------

    public <T> int getListSize(List<T> list) {

        if (list == null) {
            throw new IllegalArgumentException(
                    "List cannot be null");
        }

        return list.size();
    }

    public <T> int getSetSize(Set<T> set) {

        if (set == null) {
            throw new IllegalArgumentException(
                    "Set cannot be null");
        }

        return set.size();
    }

    public <K, V> int getMapSize(Map<K, V> map) {

        if (map == null) {
            throw new IllegalArgumentException(
                    "Map cannot be null");
        }

        return map.size();
    }
}