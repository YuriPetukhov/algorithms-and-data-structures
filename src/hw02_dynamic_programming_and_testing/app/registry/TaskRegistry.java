package hw02_dynamic_programming_and_testing.app.registry;

import hw02_dynamic_programming_and_testing.app.core.Task;

import java.util.*;

public class TaskRegistry {

    private final Map<String, Task> tasks = new LinkedHashMap<>();

    public TaskRegistry() {
        ServiceLoader<Task> loader = ServiceLoader.load(Task.class);
        for (Task task : loader) {
            String id = task.id().trim().toLowerCase(Locale.ROOT);
            if (tasks.containsKey(id)) {
                throw new IllegalStateException("Duplicate task id: " + id);
            }
            tasks.put(id, task);
        }
    }

    public Optional<Task> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(tasks.get(id.trim().toLowerCase(Locale.ROOT)));
    }

    public List<Task> list() {
        return List.copyOf(tasks.values());
    }
}