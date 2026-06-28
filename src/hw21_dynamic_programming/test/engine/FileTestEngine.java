package hw21_dynamic_programming.test.engine;

import hw21_dynamic_programming.test.adapter.FileTaskAdapter;
import hw21_dynamic_programming.test.adapter.FileTaskAdapterRegistry;
import hw21_dynamic_programming.test.bridge.MeasurableTask;
import hw21_dynamic_programming.test.bridge.ServiceBackedTask;
import hw21_dynamic_programming.test.caseflow.CaseContext;
import hw21_dynamic_programming.test.caseflow.CasePipeline;
import hw21_dynamic_programming.test.caseflow.steps.CompareStep;
import hw21_dynamic_programming.test.caseflow.steps.ExecuteServiceTaskStep;
import hw21_dynamic_programming.test.caseflow.steps.LoadExpectedStep;
import hw21_dynamic_programming.test.caseflow.steps.LoadInputStep;
import hw21_dynamic_programming.test.caseflow.steps.NormalizeStep;
import hw21_dynamic_programming.test.compare.OutputComparator;
import hw21_dynamic_programming.test.compare.TextNormalizer;
import hw21_dynamic_programming.test.config.FileTestConfig;
import hw21_dynamic_programming.test.model.FileTestCase;
import hw21_dynamic_programming.test.model.TestResult;
import hw21_dynamic_programming.test.source.FilePairTestSource;
import hw21_dynamic_programming.service.TaskExecutionHandler;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class FileTestEngine {

    private final FileTaskAdapterRegistry adapterRegistry;
    private final TaskExecutionHandler executionHandler;

    public FileTestEngine(
            FileTaskAdapterRegistry adapterRegistry,
            TaskExecutionHandler executionHandler
    ) {
        this.adapterRegistry = Objects.requireNonNull(adapterRegistry);
        this.executionHandler = Objects.requireNonNull(executionHandler);
    }

    public TestRunResult run(FileTestConfig config) throws Exception {
        FileTaskAdapter<?, ?> adapter = adapterRegistry.getRequired(config.taskId());
        return runTyped(config, adapter);
    }

    private <I, O> TestRunResult runTyped(
            FileTestConfig config,
            FileTaskAdapter<I, O> adapter
    ) throws Exception {
        MeasurableTask<I, O> task = new ServiceBackedTask<>(
                adapter,
                executionHandler
        );
        FilePairTestSource source = new FilePairTestSource(
                config.inputsDirectory(),
                config.outputsDirectory(),
                config.inputExtension(),
                config.outputExtension()
        );
        List<FileTestCase> testCases = source.load();
        CasePipeline pipeline = new CasePipeline(List.of(
                new LoadExpectedStep(StandardCharsets.UTF_8),
                new LoadInputStep(StandardCharsets.UTF_8),
                new ExecuteServiceTaskStep(),
                new NormalizeStep(new TextNormalizer(config.compareMode())),
                new CompareStep(new OutputComparator())
        ));

        List<TestResult> results = new ArrayList<>(testCases.size());
        for (FileTestCase testCase : testCases) {
            CaseContext context = new CaseContext(
                    task,
                    testCase,
                    config.benchmarkRuns(),
                    config.timeoutEnabled(),
                    config.timeoutMillis()
            );
            pipeline.execute(context);
            results.add(context.result());
        }
        return new TestRunResult(config, results);
    }
}
