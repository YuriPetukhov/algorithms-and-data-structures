package hw21_dynamic_programming.test.compare;

import java.math.BigInteger;

public final class OutputComparator {

    public boolean matches(String expected, String actual) {
        String expectedText = expected == null ? "" : expected;
        String actualText = actual == null ? "" : actual;

        Double expectedNumber = parseDouble(expectedText);
        Double actualNumber = parseDouble(actualText);
        if (expectedNumber != null && actualNumber != null) {
            return almostEqual(expectedNumber, actualNumber);
        }

        if (isInteger(expectedText)
                && isInteger(actualText)
                && (expectedText.length() >= 50 || actualText.length() >= 50)) {
            return new BigInteger(expectedText).equals(new BigInteger(actualText));
        }

        return expectedText.equals(actualText);
    }

    private static Double parseDouble(String value) {
        if (value.length() > 200 || value.indexOf('\n') >= 0) {
            return null;
        }
        try {
            return Double.parseDouble(value.replace(',', '.'));
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    private static boolean isInteger(String value) {
        if (value.isEmpty()) {
            return false;
        }
        int index = value.charAt(0) == '-' ? 1 : 0;
        if (index == value.length()) {
            return false;
        }
        while (index < value.length()) {
            if (!Character.isDigit(value.charAt(index++))) {
                return false;
            }
        }
        return true;
    }

    private static boolean almostEqual(double expected, double actual) {
        double difference = Math.abs(expected - actual);
        double tolerance = Math.max(
                2e-8,
                2e-8 * Math.max(1.0, Math.abs(expected))
        );
        return difference <= tolerance;
    }
}
