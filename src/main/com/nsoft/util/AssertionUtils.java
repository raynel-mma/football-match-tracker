package com.nsoft.util;

public class AssertionUtils {

    private AssertionUtils() {
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    public static void assertEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected == null || actual == null || !expected.equals(actual)) {
            throw new AssertionError(message);
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        String message = "Expected:" + expected.toString() + ", Actual: " + actual.toString();

        assertEquals(message, expected, actual);
    }
}
