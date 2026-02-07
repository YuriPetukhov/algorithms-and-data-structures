package hw02_dynamic_programming_and_testing.test.report;

import hw02_dynamic_programming_and_testing.test.model.TestResult;
import hw02_dynamic_programming_and_testing.test.model.TestStatus;

import java.util.List;

public class ConsoleTestReporter {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";

    public void print(List<TestResult> results) {
        int passed = 0, failed = 0, missing = 0, error = 0;

        for (TestResult r : results) {
            switch (r.status()) {
                case PASSED -> passed++;
                case FAILED -> failed++;
                case MISSING_EXPECTED -> missing++;
                case ERROR -> error++;
            }

            String line = String.format("[%s] %s | %.3f ms",
                    r.status(), r.testName(), r.timeNanos() / 1_000_000.0);

            System.out.println(color(r.status(), line));

            if (r.status() == TestStatus.FAILED) {
                System.out.println("  expected: " + r.expected());
                System.out.println("  actual  : " + r.actual());
            } else if (r.status() == TestStatus.ERROR || r.status() == TestStatus.MISSING_EXPECTED) {
                System.out.println("  message : " + r.message());
            }
        }

        System.out.println("--------------------------------");
        System.out.printf("Total: %d, Passed: %d, Failed: %d, Missing: %d, Error: %d%n",
                results.size(), passed, failed, missing, error);
    }

    public static void printFatal(Exception e) {
        System.err.println("Ошибка: " + e.getMessage());
    }

    private String color(TestStatus status, String s) {
        return switch (status) {
            case PASSED -> GREEN + s + RESET;
            case FAILED, ERROR -> RED + s + RESET;
            case MISSING_EXPECTED -> YELLOW + s + RESET;
        };
    }
}