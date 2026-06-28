package hw21_dynamic_programming.service.bootstrap;

import hw21_dynamic_programming.registry.TaskRegistry;
import hw21_dynamic_programming.service.TaskExecutionHandler;

import java.util.Objects;

public record TaskServiceRuntime(
        TaskRegistry taskRegistry,
        TaskExecutionHandler executionHandler
) {
    public TaskServiceRuntime {
        Objects.requireNonNull(taskRegistry, "Task registry must not be null.");
        Objects.requireNonNull(
                executionHandler,
                "Execution handler must not be null."
        );
    }
}
