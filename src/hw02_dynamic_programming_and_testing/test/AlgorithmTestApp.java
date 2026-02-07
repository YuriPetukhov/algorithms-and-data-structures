package hw02_dynamic_programming_and_testing.test;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.app.registry.TaskRegistry;
import hw02_dynamic_programming_and_testing.test.config.*;
import hw02_dynamic_programming_and_testing.test.report.ConsoleTestReporter;
import hw02_dynamic_programming_and_testing.test.source.FilePairTestSource;
import hw02_dynamic_programming_and_testing.test.strategy.FilePairTestStrategy;
import hw02_dynamic_programming_and_testing.test.suite.TestContext;
import hw02_dynamic_programming_and_testing.test.strategy.TestStrategy;

import java.nio.charset.StandardCharsets;

public class AlgorithmTestApp {

    public static void main(String[] args) {
        try {
            TestConfigProvider configProvider = new DefaultTestConfigProvider(
                    new PropertiesTestConfigProvider(new PropertiesLoader(), "hw02/application.properties"),
                    new ArgsOverrideProvider()
            );

            TestConfig cfg = configProvider.provide(args);

            if (cfg.taskId() == null || cfg.taskId().isBlank()) {
                throw new IllegalArgumentException(
                        "Missing test.task.id in application.properties (or pass --task <id>)"
                );
            }

            TaskRegistry registry = new TaskRegistry();
            Task task = registry.findById(cfg.taskId())
                    .orElseThrow(() -> new IllegalArgumentException("Unknown task id: " + cfg.taskId()));

            FilePairTestSource source = new FilePairTestSource(
                    cfg.inputsDir(),
                    cfg.outputsDir(),
                    cfg.inputExt(),
                    cfg.outputExt()
            );

            TestContext ctx = new TestContext(task);
            TestStrategy strategy = new FilePairTestStrategy(source, cfg, StandardCharsets.UTF_8);

            strategy.run(ctx);

        } catch (Exception e) {
            ConsoleTestReporter.printFatal(e);
        }
    }
}
