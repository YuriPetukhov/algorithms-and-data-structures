package hw21_dynamic_programming.test.report;

import hw21_dynamic_programming.test.config.FileTestConfig;
import hw21_dynamic_programming.test.engine.TestRunResult;
import hw21_dynamic_programming.test.model.TestResult;
import hw21_dynamic_programming.test.model.TestStatus;

public final class ConsoleTestReporter {

    public void print(TestRunResult run) {
        FileTestConfig config = run.config();
        int passed = 0;
        int failed = 0;
        int timeout = 0;
        int missing = 0;
        int errors = 0;

        System.out.println("Task: " + config.taskId());
        System.out.println("Tests: " + config.testDir().toAbsolutePath());
        System.out.println("--------------------------------");

        for (TestResult result : run.results()) {
            switch (result.status()) {
                case PASSED -> passed++;
                case FAILED -> failed++;
                case TIMEOUT -> timeout++;
                case MISSING_EXPECTED -> missing++;
                case ERROR -> errors++;
            }

            if (result.status() == TestStatus.PASSED && !config.showPassed()) {
                continue;
            }

            System.out.printf(
                    "[%s] %s | %.3f ms%n",
                    result.status(),
                    result.testName(),
                    result.timeNanos() / 1_000_000.0
            );

            if (result.status() == TestStatus.FAILED && config.showDiff()) {
                System.out.println("  expected: " + preview(result.expected()));
                System.out.println("  actual  : " + preview(result.actual()));
            } else if (result.message() != null) {
                System.out.println("  message : " + result.message());
            }
        }

        System.out.println("--------------------------------");
        System.out.printf(
                "Total: %d, Passed: %d, Failed: %d, Timeout: %d, Missing: %d, Error: %d%n",
                run.results().size(),
                passed,
                failed,
                timeout,
                missing,
                errors
        );
    }

    public void printFatal(Throwable error) {
        System.err.println(
                "Fatal error: "
                        + error.getClass().getSimpleName()
                        + ": "
                        + (error.getMessage() == null ? "" : error.getMessage())
        );
    }

    private static String preview(String text) {
        if (text == null) {
            return "null";
        }
        String normalized = text.replace("\n", "\\n");
        return normalized.length() <= 160
                ? normalized
                : normalized.substring(0, 160) + "...";
    }
}
