package hw02_dynamic_programming_and_testing.test.suite.steps;

import hw02_dynamic_programming_and_testing.test.model.TestResult;
import hw02_dynamic_programming_and_testing.test.caseflow.CaseRunner;
import hw02_dynamic_programming_and_testing.test.suite.TestContext;
import hw02_dynamic_programming_and_testing.test.suite.TestStep;

import java.util.List;

public class RunCasesStep implements TestStep {

    private final CaseRunner runner;

    public RunCasesStep(CaseRunner runner) {
        this.runner = runner;
    }

    @Override
    public void execute(TestContext ctx) {
        List<TestResult> results = runner.runAll(ctx.task(), ctx.cases());
        ctx.setResults(results);
    }
}