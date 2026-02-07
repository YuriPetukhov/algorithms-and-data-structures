package hw02_dynamic_programming_and_testing.test.runner;

import hw02_dynamic_programming_and_testing.test.model.TestResult;

import java.util.List;

public class CasePipelineHandler {

    private final List<CaseStep> steps;

    public CasePipelineHandler(List<CaseStep> steps) {
        this.steps = steps;
    }

    public void run(CaseContext ctx) {
        try {
            for (CaseStep step : steps) {
                step.execute(ctx);

                if (ctx.result() != null) {
                    break;
                }
            }
        } catch (Exception e) {
            ctx.setResult(TestResult.error(ctx.testCase().name(), e));
        }
    }
}
