package hw02_dynamic_programming_and_testing.test.config;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public record TestConfig(
        String taskId,
        Path testDir,
        String inputExt,
        String outputExt,
        CompareMode compareMode,
        boolean showPassed,
        boolean showFailed,
        boolean showDiff,
        boolean timeEnabled,
        TimeUnit timeUnit,
        int benchmarkRuns
) {
    public Path inputsDir() {
        return testDir.resolve("inputs");
    }

    public Path outputsDir() {
        return testDir.resolve("outputs");
    }
}
