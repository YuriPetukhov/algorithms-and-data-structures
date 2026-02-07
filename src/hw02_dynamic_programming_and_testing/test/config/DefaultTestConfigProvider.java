package hw02_dynamic_programming_and_testing.test.config;

import java.nio.file.Path;

public class DefaultTestConfigProvider implements TestConfigProvider {

    private final TestConfigProvider baseProvider;
    private final ArgsOverrideProvider overrideProvider;

    public DefaultTestConfigProvider(TestConfigProvider baseProvider, ArgsOverrideProvider overrideProvider) {
        this.baseProvider = baseProvider;
        this.overrideProvider = overrideProvider;
    }

    @Override
    public TestConfig provide(String[] args) throws Exception {
        TestConfig base = baseProvider.provide(args);
        ArgsOverride ov = overrideProvider.read(args);

        String taskId = (ov.taskId() != null) ? ov.taskId() : base.taskId();
        Path testDir = (ov.testDir() != null) ? ov.testDir() : base.testDir();

        return new TestConfig(
                taskId,
                testDir,
                base.inputExt(),
                base.outputExt(),
                base.compareMode(),
                base.showPassed(),
                base.showFailed(),
                base.showDiff(),
                base.timeEnabled(),
                base.timeUnit()
        );
    }
}
