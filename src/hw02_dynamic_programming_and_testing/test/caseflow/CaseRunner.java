package hw02_dynamic_programming_and_testing.test.caseflow;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.test.model.TestCase;
import hw02_dynamic_programming_and_testing.test.model.TestResult;
import hw02_dynamic_programming_and_testing.test.suite.TestContext;

import java.util.ArrayList;
import java.util.List;

public class CaseRunner {

    private final CasePipelineHandler handler;

    public CaseRunner(CasePipelineHandler handler) {
        this.handler = handler;
    }

    public List<TestResult> runAll(TestContext testCtx) {

        List<TestResult> results = new ArrayList<>();

        Task task = testCtx.task();
        int runs = testCtx.benchmarkRuns();

        for (TestCase tc : testCtx.cases()) {
            CaseContext ctx = new CaseContext(task, tc);
            ctx.setBenchmarkRuns(runs);
            handler.run(ctx);
            results.add(ctx.result());
        }

        return results;
    }
}
