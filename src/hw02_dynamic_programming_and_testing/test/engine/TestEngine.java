package hw02_dynamic_programming_and_testing.test.engine;

import hw02_dynamic_programming_and_testing.test.config.TestConfig;

public interface TestEngine {
    TestRunResult run(TestConfig cfg, int runs) throws Exception;
}
