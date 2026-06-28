package hw21_dynamic_programming.test.caseflow;

import hw21_dynamic_programming.test.model.TestResult;

import java.util.List;

public final class CasePipeline {

    private final List<CaseStep> steps;

    public CasePipeline(List<? extends CaseStep> steps) {
        this.steps = List.copyOf(steps);
    }

    public void execute(CaseContext context) {
        try {
            for (CaseStep step : steps) {
                step.execute(context);
                if (context.result() != null) {
                    return;
                }
            }
        } catch (Throwable error) {
            context.result(TestResult.error(context.testCase().name(), error));
        }
    }
}
