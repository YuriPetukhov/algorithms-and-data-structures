package hw02_dynamic_programming_and_testing.test.strategy;

import hw02_dynamic_programming_and_testing.test.caseflow.steps.*;
import hw02_dynamic_programming_and_testing.test.compare.TextNormalizationStrategy;
import hw02_dynamic_programming_and_testing.test.config.TestConfig;
import hw02_dynamic_programming_and_testing.test.compare.NormalizationStrategyFactory;
import hw02_dynamic_programming_and_testing.test.report.ConsoleTestReporter;
import hw02_dynamic_programming_and_testing.test.caseflow.*;
import hw02_dynamic_programming_and_testing.test.source.FilePairTestSource;
import hw02_dynamic_programming_and_testing.test.suite.PipelineHandler;
import hw02_dynamic_programming_and_testing.test.suite.TestContext;
import hw02_dynamic_programming_and_testing.test.suite.TestStep;
import hw02_dynamic_programming_and_testing.test.suite.steps.LoadCasesStep;
import hw02_dynamic_programming_and_testing.test.suite.steps.ReportStep;
import hw02_dynamic_programming_and_testing.test.suite.steps.RunCasesStep;

import java.nio.charset.Charset;
import java.util.List;

public class FilePairTestStrategy implements TestStrategy {

    private final FilePairTestSource source;
    private final ConsoleTestReporter reporter;
    private final CaseRunner runner;

    public FilePairTestStrategy(FilePairTestSource source, TestConfig cfg, Charset charset) {
        this.source = source;
        this.reporter = new ConsoleTestReporter();

        TextNormalizationStrategy norm =
                NormalizationStrategyFactory.from(cfg.compareMode());

        CasePipelineHandler handler = new CasePipelineHandler(List.of(
                new LoadExpectedStep(charset),
                new LoadInputStep(charset),
                new ExecuteTaskStep(),
                new NormalizeStep(norm),
                new CompareStep()
        ));

        this.runner = new CaseRunner(handler);
    }

    @Override
    public void run(TestContext ctx) throws Exception {
        List<TestStep> steps = List.of(
                new LoadCasesStep(source),
                new RunCasesStep(runner),
                new ReportStep(reporter)
        );

        new PipelineHandler(steps).run(ctx);
    }
}
