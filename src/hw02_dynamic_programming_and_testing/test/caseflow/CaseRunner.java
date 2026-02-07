package hw02_dynamic_programming_and_testing.test.caseflow;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.test.model.TestCase;
import hw02_dynamic_programming_and_testing.test.model.TestResult;

import java.util.ArrayList;
import java.util.List;

public class CaseRunner {

    private final CasePipelineHandler handler;

    public CaseRunner(CasePipelineHandler handler) {
        this.handler = handler;
    }

    public List<TestResult> runAll(Task task, List<TestCase> cases) {
        List<TestResult> results = new ArrayList<>();

        for (TestCase tc : cases) {
            CaseContext ctx = new CaseContext(task, tc);
            handler.run(ctx);
            results.add(ctx.result());
        }

        return results;
    }
}
