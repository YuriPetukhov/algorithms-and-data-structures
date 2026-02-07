package hw02_dynamic_programming_and_testing.app.bootstrap;

import hw02_dynamic_programming_and_testing.app.core.Task;
import hw02_dynamic_programming_and_testing.app.registry.TaskRegistry;

import java.util.HashMap;
import java.util.Map;

public class ArgsTaskProvider implements TaskProvider {

    private final TaskRegistry registry;

    public ArgsTaskProvider(TaskRegistry registry) {
        if (registry == null) throw new IllegalArgumentException("registry must not be null");
        this.registry = registry;
    }

    @Override
    public SelectedTask provide(String[] args) {
        Map<String, String> m = parseArgs(args);

        String taskId = m.get("task");
        if (taskId == null || taskId.isBlank()) {
            throw new IllegalArgumentException("Missing --task <id>");
        }

        Task task = registry.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown task id: " + taskId));

        String input = m.get("input");
        if (input == null) input = m.get("n");
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Missing --input <text> (or --n <number>)");
        }

        return new SelectedTask(task, input);
    }

    private Map<String, String> parseArgs(String[] args) {
        Map<String, String> m = new HashMap<>();
        if (args == null) return m;

        for (int i = 0; i < args.length; i++) {
            String a = args[i];
            if (a == null || !a.startsWith("--")) continue;

            String key = a.substring(2).trim();
            if (key.isEmpty()) continue;

            String value = null;
            if (i + 1 < args.length) {
                String next = args[i + 1];
                if (next != null && !next.startsWith("--")) {
                    value = next;
                    i++;
                }
            }
            if (value != null) {
                m.put(key, value);
            }
        }
        return m;
    }
}
