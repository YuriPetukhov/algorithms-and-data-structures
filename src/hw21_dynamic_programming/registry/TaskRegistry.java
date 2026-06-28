package hw21_dynamic_programming.registry;

import hw21_dynamic_programming.tasks.api.TaskDefinition;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface TaskRegistry {

    List<TaskDefinition<?, ?>> getAll();

    Optional<TaskDefinition<?, ?>> findById(String taskId);

    default TaskDefinition<?, ?> getRequired(String taskId) {
        return findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found: " + taskId));
    }
}
