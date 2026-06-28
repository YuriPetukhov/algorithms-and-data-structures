package hw21_dynamic_programming.registry;

import hw21_dynamic_programming.tasks.api.TaskDefinition;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class InMemoryTaskRegistry implements TaskRegistry {

    private final Map<String, TaskDefinition<?, ?>> tasksById;

    public InMemoryTaskRegistry(Collection<? extends TaskDefinition<?, ?>> tasks) {
        Objects.requireNonNull(tasks, "Task collection must not be null.");

        Map<String, TaskDefinition<?, ?>> registry = new LinkedHashMap<>();
        for (TaskDefinition<?, ?> task : tasks) {
            Objects.requireNonNull(task, "Registered task must not be null.");
            TaskDefinition<?, ?> previous = registry.putIfAbsent(task.id(), task);
            if (previous != null) {
                throw new IllegalArgumentException("Duplicate task id: " + task.id());
            }
        }
        this.tasksById = Collections.unmodifiableMap(new LinkedHashMap<>(registry));
    }

    @Override
    public List<TaskDefinition<?, ?>> getAll() {
        return List.copyOf(tasksById.values());
    }

    @Override
    public Optional<TaskDefinition<?, ?>> findById(String taskId) {
        if (taskId == null || taskId.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(tasksById.get(taskId));
    }
}
