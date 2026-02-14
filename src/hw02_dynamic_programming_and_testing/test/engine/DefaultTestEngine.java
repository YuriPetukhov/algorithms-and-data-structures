package hw02_dynamic_programming_and_testing.test.engine;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.app.registry.TaskRegistry;
import hw02_dynamic_programming_and_testing.test.config.TestConfig;
import hw02_dynamic_programming_and_testing.test.source.FilePairTestSource;
import hw02_dynamic_programming_and_testing.test.strategy.FilePairTestStrategy;
import hw02_dynamic_programming_and_testing.test.strategy.TestStrategy;
import hw02_dynamic_programming_and_testing.test.suite.TestContext;

import java.nio.charset.StandardCharsets;

public class DefaultTestEngine implements TestEngine {

    private final TaskRegistry registry;

    public DefaultTestEngine(TaskRegistry registry) {
        this.registry = registry;
    }

    @Override
    public TestRunResult run(TestConfig cfg, int runs) throws Exception {
        if (cfg.taskId() == null || cfg.taskId().isBlank()) {
            throw new IllegalArgumentException("Missing test.task.id (or pass --task <id>)");
        }

        Task task = registry.findById(cfg.taskId())
                .orElseThrow(() -> new IllegalArgumentException("Unknown task id: " + cfg.taskId()));

        FilePairTestSource source = new FilePairTestSource(
                cfg.inputsDir(),
                cfg.outputsDir(),
                cfg.inputExt(),
                cfg.outputExt()
        );

        TestContext ctx = new TestContext(task);
        ctx.setBenchmarkRuns(runs);
        TestStrategy strategy = new FilePairTestStrategy(source, cfg, StandardCharsets.UTF_8);

        strategy.run(ctx);

        return new TestRunResult(cfg, ctx.results());
    }

}
