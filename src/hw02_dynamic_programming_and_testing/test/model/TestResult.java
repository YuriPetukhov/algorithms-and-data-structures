package hw02_dynamic_programming_and_testing.test.model;

public record TestResult(
        String testName,
        TestStatus status,
        long timeNanos,
        String expected,
        String actual,
        String message
) {

    public static TestResult passed(String testName, long timeNanos) {
        return new TestResult(testName, TestStatus.PASSED, timeNanos, null, null, null);
    }

    public static TestResult failed(String testName, long timeNanos, String expected, String actual) {
        return new TestResult(testName, TestStatus.FAILED, timeNanos, expected, actual, "Output mismatch");
    }

    public static TestResult missingExpected(String testName) {
        return new TestResult(testName, TestStatus.MISSING_EXPECTED, 0, null, null, "Missing .out file");
    }

    public static TestResult error(String testName, Exception e) {
        String msg = e.getClass().getSimpleName() + ": " + (e.getMessage() == null ? "" : e.getMessage());
        return new TestResult(testName, TestStatus.ERROR, 0, null, null, msg);
    }
}