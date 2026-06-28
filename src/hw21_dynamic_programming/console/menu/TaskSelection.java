package hw21_dynamic_programming.console.menu;

public record TaskSelection(
        String taskId,
        boolean exitRequested
) {

    public TaskSelection {
        if (!exitRequested && (taskId == null || taskId.isBlank())) {
            throw new IllegalArgumentException(
                    "Task id is required for an execution selection."
            );
        }
    }

    public static TaskSelection execute(String taskId) {
        return new TaskSelection(taskId, false);
    }

    public static TaskSelection exit() {
        return new TaskSelection(null, true);
    }
}
