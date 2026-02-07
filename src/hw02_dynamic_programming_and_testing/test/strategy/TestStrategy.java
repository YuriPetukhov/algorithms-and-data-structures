package hw02_dynamic_programming_and_testing.test.strategy;

import hw02_dynamic_programming_and_testing.test.suite.TestContext;

public interface TestStrategy {
    void run(TestContext ctx) throws Exception;
}
