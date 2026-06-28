package hw21_dynamic_programming.test.engine;

import hw21_dynamic_programming.test.config.FileTestConfig;
import hw21_dynamic_programming.test.model.TestResult;
import hw21_dynamic_programming.test.model.TestStatus;

import java.util.List;

public record TestRunResult(
        FileTestConfig config,
        List<TestResult> results
) {
    public TestRunResult {
        results = List.copyOf(results);
    }

    public boolean successful() {
        return results.stream().allMatch(
                result -> result.status() == TestStatus.PASSED
        );
    }
}
