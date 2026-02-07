package hw02_dynamic_programming_and_testing.test.suite.steps;

import hw02_dynamic_programming_and_testing.test.report.ConsoleTestReporter;
import hw02_dynamic_programming_and_testing.test.suite.TestContext;
import hw02_dynamic_programming_and_testing.test.suite.TestStep;

public class ReportStep implements TestStep {

    private final ConsoleTestReporter reporter;

    public ReportStep(ConsoleTestReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    public void execute(TestContext ctx) {
        reporter.print(ctx.results());
    }
}
