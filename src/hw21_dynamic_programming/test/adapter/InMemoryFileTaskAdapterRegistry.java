package hw21_dynamic_programming.test.adapter;

import hw21_dynamic_programming.registry.TaskRegistry;
import hw21_dynamic_programming.tasks.api.TaskDefinition;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class InMemoryFileTaskAdapterRegistry
        implements FileTaskAdapterRegistry {

    private final Map<String, FileTaskAdapter<?, ?>> adaptersByTaskId;

    public InMemoryFileTaskAdapterRegistry(
            Collection<? extends FileTaskAdapter<?, ?>> adapters,
            TaskRegistry taskRegistry
    ) {
        Objects.requireNonNull(adapters, "File task adapters must not be null.");
        Objects.requireNonNull(taskRegistry, "Task registry must not be null.");

        Map<String, FileTaskAdapter<?, ?>> registry = new LinkedHashMap<>();
        for (FileTaskAdapter<?, ?> adapter : adapters) {
            validateAdapter(adapter);
            TaskDefinition<?, ?> task = taskRegistry.getRequired(adapter.taskId());
            verifyCompatibility(task, adapter);
            FileTaskAdapter<?, ?> previous = registry.putIfAbsent(
                    adapter.taskId(),
                    adapter
            );
            if (previous != null) {
                throw new IllegalArgumentException(
                        "Duplicate file task adapter: " + adapter.taskId()
                );
            }
        }

        this.adaptersByTaskId = Map.copyOf(registry);
    }

    @Override
    public FileTaskAdapter<?, ?> getRequired(String taskId) {
        FileTaskAdapter<?, ?> adapter = adaptersByTaskId.get(taskId);
        if (adapter == null) {
            throw new NoSuchElementException(
                    "File task adapter not found for task: " + taskId
            );
        }
        return adapter;
    }

    private static void validateAdapter(FileTaskAdapter<?, ?> adapter) {
        Objects.requireNonNull(adapter, "File task adapter must not be null.");
        if (adapter.taskId() == null || adapter.taskId().isBlank()) {
            throw new IllegalArgumentException("Adapter task id must not be blank.");
        }
        Objects.requireNonNull(adapter.inputType(), "Adapter input type must not be null.");
        Objects.requireNonNull(adapter.resultType(), "Adapter result type must not be null.");
    }

    private static void verifyCompatibility(
            TaskDefinition<?, ?> task,
            FileTaskAdapter<?, ?> adapter
    ) {
        if (!task.inputType().equals(adapter.inputType())) {
            throw new IllegalArgumentException(
                    "Input type mismatch for file adapter of task: " + task.id()
            );
        }
        if (!task.resultType().equals(adapter.resultType())) {
            throw new IllegalArgumentException(
                    "Result type mismatch for file adapter of task: " + task.id()
            );
        }
    }
}
