package hw02_dynamic_programming_and_testing.test.engine;

import hw02_dynamic_programming_and_testing.test.config.TestConfig;
import hw02_dynamic_programming_and_testing.test.model.TestResult;

public record TestRunResult(
        TestConfig cfg,
        java.util.List<TestResult> results
) {}
