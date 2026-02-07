package hw02_dynamic_programming_and_testing.app.bootstrap;

import hw02_dynamic_programming_and_testing.app.core.Task;

public record SelectedTask(Task task, String input) {

    public SelectedTask {
        if (task == null) {
            throw new IllegalArgumentException("hw02_dynamic_programming_and_testing.app.core.Task must not be null");
        }
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
    }
}
