package hw02_dynamic_programming_and_testing.test.strategy;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.test.report.ConsoleTestReporter;
import hw02_dynamic_programming_and_testing.test.runner.TaskTestRunner;
import hw02_dynamic_programming_and_testing.test.source.FilePairTestSource;

import java.nio.charset.Charset;
import java.util.List;

public class FilePairTestStrategy implements TestStrategy {

    private final FilePairTestSource source;
    private final TaskTestRunner runner;
    private final ConsoleTestReporter reporter;

    public FilePairTestStrategy(FilePairTestSource source, Charset charset) {
        this.source = source;
        this.runner = new TaskTestRunner(charset);
        this.reporter = new ConsoleTestReporter();
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
