package hw21_dynamic_programming.test.model;

public record TestResult(
        String testName,
        TestStatus status,
        long timeNanos,
        String expected,
        String actual,
        String message
) {
    public static TestResult passed(String name, long nanos) {
        return new TestResult(name, TestStatus.PASSED, nanos, null, null, null);
    }

    public static TestResult failed(
            String name,
            long nanos,
            String expected,
            String actual
    ) {
        return new TestResult(
                name,
                TestStatus.FAILED,
                nanos,
                expected,
                actual,
                "Output mismatch"
        );
    }

    public static TestResult missingExpected(String name) {
        return new TestResult(
                name,
                TestStatus.MISSING_EXPECTED,
                0,
                null,
                null,
                "Expected output file is missing."
        );
    }

    public static TestResult timeout(String name, long nanos) {
        return new TestResult(
                name,
                TestStatus.TIMEOUT,
                nanos,
                null,
                null,
                "Execution timeout."
        );
    }

    public static TestResult error(String name, Throwable error) {
        String message = error.getClass().getSimpleName()
                + ": "
                + (error.getMessage() == null ? "" : error.getMessage());
        return new TestResult(
                name,
                TestStatus.ERROR,
                0,
                null,
                null,
                message
        );
    }
}
