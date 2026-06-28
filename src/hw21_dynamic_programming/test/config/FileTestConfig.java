package hw21_dynamic_programming.test.config;

import java.nio.file.Path;

public record FileTestConfig(
        String taskId,
        Path testDir,
        String inputExtension,
        String outputExtension,
        CompareMode compareMode,
        int benchmarkRuns,
        boolean timeoutEnabled,
        long timeoutMillis,
        boolean showPassed,
        boolean showDiff
) {
    public FileTestConfig {
        if (taskId == null || taskId.isBlank()) {
            throw new IllegalArgumentException("Task id must not be blank.");
        }
        if (testDir == null) {
            throw new IllegalArgumentException("Test directory must not be null.");
        }
        inputExtension = normalizeExtension(inputExtension, ".in");
        outputExtension = normalizeExtension(outputExtension, ".out");
        if (benchmarkRuns < 1) {
            throw new IllegalArgumentException("Benchmark runs must be positive.");
        }
        if (timeoutEnabled && timeoutMillis < 1) {
            throw new IllegalArgumentException("Timeout must be positive when enabled.");
        }
    }

    public Path inputsDirectory() {
        return testDir.resolve("inputs");
    }

    public Path outputsDirectory() {
        return testDir.resolve("outputs");
    }

    private static String normalizeExtension(String extension, String defaultValue) {
        if (extension == null || extension.isBlank()) {
            return defaultValue;
        }
        String value = extension.trim();
        return value.startsWith(".") ? value : "." + value;
    }
}
