package hw21_dynamic_programming.test.caseflow.steps;

import hw21_dynamic_programming.test.caseflow.CaseContext;
import hw21_dynamic_programming.test.caseflow.CaseStep;
import hw21_dynamic_programming.test.compare.OutputComparator;
import hw21_dynamic_programming.test.model.TestResult;

public final class CompareStep implements CaseStep {

    private final OutputComparator comparator;

    public CompareStep(OutputComparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public void execute(CaseContext context) {
        if (comparator.matches(
                context.expectedNormalized(),
                context.actualNormalized()
        )) {
            context.result(TestResult.passed(
                    context.testCase().name(),
                    context.timeNanos()
            ));
            return;
        }
        context.result(TestResult.failed(
                context.testCase().name(),
                context.timeNanos(),
                context.expectedNormalized(),
                context.actualNormalized()
        ));
    }
}
