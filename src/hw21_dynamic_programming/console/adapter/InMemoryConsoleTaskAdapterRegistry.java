package hw21_dynamic_programming.console.adapter;

import hw21_dynamic_programming.registry.TaskRegistry;
import hw21_dynamic_programming.tasks.api.TaskDefinition;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class InMemoryConsoleTaskAdapterRegistry
        implements ConsoleTaskAdapterRegistry {

    private final Map<String, ConsoleTaskAdapter<?, ?>> adaptersByTaskId;

    public InMemoryConsoleTaskAdapterRegistry(
            Collection<? extends ConsoleTaskAdapter<?, ?>> adapters,
            TaskRegistry taskRegistry
    ) {
        Objects.requireNonNull(adapters, "Console adapters must not be null.");
        Objects.requireNonNull(taskRegistry, "Task registry must not be null.");

        Map<String, ConsoleTaskAdapter<?, ?>> registry = new LinkedHashMap<>();
        for (ConsoleTaskAdapter<?, ?> adapter : adapters) {
            validateAdapter(adapter);

            TaskDefinition<?, ?> task = taskRegistry.getRequired(adapter.taskId());
            verifyCompatibility(task, adapter);

            ConsoleTaskAdapter<?, ?> previous = registry.putIfAbsent(
                    adapter.taskId(),
                    adapter
            );
            if (previous != null) {
                throw new IllegalArgumentException(
                        "Duplicate console adapter for task: " + adapter.taskId()
                );
            }
        }

        for (TaskDefinition<?, ?> task : taskRegistry.getAll()) {
            if (!registry.containsKey(task.id())) {
                throw new IllegalArgumentException(
                        "Console adapter is missing for task: " + task.id()
                );
            }
        }

        this.adaptersByTaskId = Map.copyOf(registry);
    }

    @Override
    public ConsoleTaskAdapter<?, ?> getRequired(String taskId) {
        ConsoleTaskAdapter<?, ?> adapter = adaptersByTaskId.get(taskId);
        if (adapter == null) {
            throw new NoSuchElementException(
                    "Console adapter not found for task: " + taskId
            );
        }
        return adapter;
    }

    private static void validateAdapter(ConsoleTaskAdapter<?, ?> adapter) {
        Objects.requireNonNull(adapter, "Console adapter must not be null.");
        if (adapter.taskId() == null || adapter.taskId().isBlank()) {
            throw new IllegalArgumentException("Adapter task id must not be blank.");
        }
        Objects.requireNonNull(adapter.inputType(), "Adapter input type must not be null.");
        Objects.requireNonNull(adapter.resultType(), "Adapter result type must not be null.");
        Objects.requireNonNull(adapter.inputForm(), "Adapter input form must not be null.");
        Objects.requireNonNull(adapter.resultView(), "Adapter result view must not be null.");
    }

    private static void verifyCompatibility(
            TaskDefinition<?, ?> task,
            ConsoleTaskAdapter<?, ?> adapter
    ) {
        if (!task.inputType().equals(adapter.inputType())) {
            throw new IllegalArgumentException(
                    "Console input type mismatch for task: " + task.id()
            );
        }
        if (!task.resultType().equals(adapter.resultType())) {
            throw new IllegalArgumentException(
                    "Console result type mismatch for task: " + task.id()
            );
        }
    }
}
