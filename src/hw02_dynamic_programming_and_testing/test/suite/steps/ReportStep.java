package hw02_dynamic_programming_and_testing.test.strategy;

import hw02_dynamic_programming_and_testing.test.report.ConsoleTestReporter;

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
