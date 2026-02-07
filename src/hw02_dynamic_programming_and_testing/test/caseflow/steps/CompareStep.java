package hw02_dynamic_programming_and_testing.test.caseflow;

import hw02_dynamic_programming_and_testing.test.model.TestResult;

public class CompareStep implements CaseStep {

    @Override
    public void execute(CaseContext ctx) {
        boolean ok = ctx.expectedNorm().equals(ctx.actualNorm());
        if (ok) {
            ctx.setResult(TestResult.passed(ctx.testCase().name(), ctx.timeNanos()));
        } else {
            ctx.setResult(TestResult.failed(
                    ctx.testCase().name(),
                    ctx.timeNanos(),
                    ctx.expectedNorm(),
                    ctx.actualNorm()
            ));
        }
    }
}
