package hw02_dynamic_programming_and_testing.test.config;

import java.nio.file.Path;

public record ArgsOverride(
        String taskId,
        Path testDir
) {}
